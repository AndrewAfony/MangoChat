package andrewafony.testapp.auth.login

import andrewafony.testapp.shared_ui.StatefulViewModel
import andrewafony.testapp.domain.repository.AuthRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
) : andrewafony.testapp.shared_ui.StatefulViewModel<AuthUiState, UiEvent>(AuthUiState()) {

    fun handleButton(fullNumber: String) = viewModelScope.launch {
        if (state.value.screenState is AuthState.EnterCode) {
            authRepository.checkAuthCode(fullNumber, state.value.code)
                .onStart { updateState { copy(screenState = AuthState.Loading) } }
                .collect { result ->
                    with(result) {
                        onSuccess { isUserExists ->
                            sendEvent(if (isUserExists) UiEvent.NavigateToHome else UiEvent.NavigateToReg)
                        }
                        onFailure { error ->
                            updateState { copy(screenState = AuthState.EnterCode) }
                            sendEvent(UiEvent.Error(error.message ?: "Unknown error"))
                        }
                    }
                }

        } else if (state.value.screenState is AuthState.EnterPhone) {
            authRepository.sendAuthCode(fullNumber)
                .onStart { updateState { copy(screenState = AuthState.Loading) } }
                .collect { result ->
                    with(result) {
                        onSuccess {
                            updateState { copy(isCode = true, screenState = AuthState.EnterCode) }
                            sendEvent(UiEvent.MoveFocus)
                        }
                        onFailure {
                            updateState { copy(screenState = AuthState.EnterPhone) }
                            sendEvent(UiEvent.Error(it.message ?: "Unknown error"))
                        }
                    }
                }
        }
    }

    fun updatePhone(phone: String) {
        updateState { copy(phone = phone) }
    }

    fun updateCode(code: String) {
        updateState { copy(code = code, isCodeValid = code.length == 6) }
    }

    fun backToPhone() {
        updateState {
            copy(
                screenState = AuthState.EnterPhone,
                code = "",
                isCode = false
            )
        }
    }
}

sealed interface UiEvent {

    data object MoveFocus : UiEvent

    data class Error(val message: String) : UiEvent

    data object NavigateToHome : UiEvent

    data object NavigateToReg : UiEvent
}

data class AuthUiState(
    val screenState: AuthState = AuthState.EnterPhone,
    val phone: String = "",
    val code: String = "",
    val isCode: Boolean = false,
    val isCodeValid: Boolean = false,
)

sealed interface AuthState {

    data object Loading : AuthState

    data object EnterPhone : AuthState

    data object EnterCode : AuthState
}