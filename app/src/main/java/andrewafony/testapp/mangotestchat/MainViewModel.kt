package andrewafony.testapp.mangotestchat

import andrewafony.testapp.data.utils.TokenManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    val state = combine(
        tokenManager.accessToken, tokenManager.refreshToken
    ) { access, refresh ->
        MainActivityState(
            screenState = MainActivityScreenState.Success,
            isLogged = access.isNotBlank() && refresh.isNotBlank()
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainActivityState()
        )
}

data class MainActivityState(
    val screenState: MainActivityScreenState = MainActivityScreenState.Loading,
    val isLogged: Boolean = false
)

sealed interface MainActivityScreenState {

    data object Loading : MainActivityScreenState

    data object Success : MainActivityScreenState

}