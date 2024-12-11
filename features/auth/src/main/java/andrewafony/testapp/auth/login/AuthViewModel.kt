package andrewafony.testapp.auth.login

import andrewafony.testapp.domain.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import andrewafony.testapp.domain.model.Result
import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.SignedOut)
    val authState: StateFlow<AuthState> = _authState

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState

    private val _error = MutableSharedFlow<Boolean>()
    val error: SharedFlow<Boolean> = _error

    fun handleButton(fullNumber: String) {
        if (authState.value is AuthState.EnterCode) {
            _authState.value = AuthState.Loading
            viewModelScope.launch {
                val result =
                    authRepository.checkAuthCode(fullNumber, authUiState.value.code)
                withContext(Dispatchers.Main) {
                    _authState.value =
                        when (result) {
                            is Result.Success -> {
                                if (result.data) AuthState.SignIn else AuthState.Registration
                            }

                            is Result.Error -> {
                                _error.emit(true)
                                AuthState.EnterCode
                            }
                        }
                }
            }
        } else if (authState.value is AuthState.SignedOut) {
            _authState.value = AuthState.Loading
            viewModelScope.launch {
                val result = authRepository.sendAuthCode(fullNumber)
                withContext(Dispatchers.Main) {
                    if (result) {
                        _authUiState.update { it.copy(isCode = true) }
                        _authState.value = AuthState.EnterCode
                    } else {
                        _error.emit(true)
                        _authState.value = AuthState.SignedOut
                    }
                }
            }
        }
    }

    fun clear() {
        backToPhone()
        _authUiState.value = AuthUiState("", "", false)
    }

    fun backToPhone() {
        _authState.value = AuthState.SignedOut
        _authUiState.update { it.copy(isCode = false) }
    }

    fun updateUiState(event: UiEvent) {
        when (event) {
            is UiEvent.Phone -> {
                _authUiState.update { it.copy(phone = event.phone) }
            }

            is UiEvent.Code -> {
                _authUiState.update { it.copy(code = event.code, isCodeValid = event.code.length == 6) }
            }
        }
    }
}

sealed interface UiEvent {

    data class Phone(val phone: String) : UiEvent

    data class Code(val code: String) : UiEvent
}

data class AuthUiState(
    val phone: String = "",
    val code: String = "",
    val isCode: Boolean = false,
    val isCodeValid: Boolean = false
)

sealed interface AuthState {

    data object SignedOut : AuthState

    data object Loading : AuthState

    data object EnterCode : AuthState

    data object Registration : AuthState

    data object SignIn : AuthState
}