package andrewafony.testapp.home

import andrewafony.testapp.domain.repository.ChatsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val chatsRepository: ChatsRepository
): ViewModel() {

    val chats = chatsRepository.chats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}