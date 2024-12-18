package andrewafony.testapp.chat

import andrewafony.testapp.domain.model.ChatMessage
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.ChatRepository
import andrewafony.testapp.domain.use_cases.GetUserInfoUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(
    getUserInfo: GetUserInfoUseCase,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    val user = getUserInfo()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = User.empty()
        )

    val messages: StateFlow<MessagesState> = chatRepository.messages()
        .map(MessagesState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MessagesState.Loading
        )

    fun sendMessage(text: String) {
        viewModelScope.launch {
            chatRepository.newMessage(text = text)
        }
    }
}

sealed interface MessagesState {

    data object Loading : MessagesState

    data class Success(
        val messages: List<ChatMessage>,
    ) : MessagesState
}


