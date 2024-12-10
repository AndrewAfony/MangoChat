package andrewafony.testapp.auth.registration

import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _regState = MutableStateFlow(RegState())
    val regState: StateFlow<RegState> = _regState

    fun updateUiState(event: UiEvent) {
        when (event) {
            is UiEvent.Name -> {
                _regState.update { it.copy(name = event.name) }
            }
            is UiEvent.Username -> {
                _regState.update { it.copy(username = event.username) }
            }
        }
    }

    fun register(phone: String) {
        _regState.update { it.copy(isLoading = true) }
        val formattedPhone = phone.filter { !it.isWhitespace() && it != '-' }
        viewModelScope.launch {
            val result = authRepository.register(formattedPhone, regState.value.name, regState.value.username)
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        _regState.update { it.copy(isSuccess = true, isLoading = false) }
                    }
                    is Result.Error -> _regState.update { it.copy(isError = true, isLoading = false) }
                }
            }
        }
    }
}

sealed interface UiEvent {

    data class Name(val name: String) : UiEvent

    data class Username(val username: String) : UiEvent
}

data class RegState(
    val name: String = "",
    val username: String = "",
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)