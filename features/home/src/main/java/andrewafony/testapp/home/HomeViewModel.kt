package andrewafony.testapp.home

import andrewafony.testapp.domain.repository.ChatsRepository
import andrewafony.testapp.domain.repository.UserPrefetch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val chatsRepository: ChatsRepository,
    private val userPrefetch: UserPrefetch,
) : ViewModel() {

    init {
        userPrefetch.prefetchUser()
    }

    val chats = chatsRepository.chats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}