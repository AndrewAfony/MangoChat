package andrewafony.testapp.auth.registration

import andrewafony.testapp.common.base.StatefulViewModel
import andrewafony.testapp.domain.repository.AuthRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val authRepository: AuthRepository,
) : StatefulViewModel<RegState, UiEvent>(RegState()) {

    fun register(phone: String) = viewModelScope.launch {
        val formattedPhone = phone.filter { !it.isWhitespace() && it != '-' }
        authRepository.register(formattedPhone, state.value.name, state.value.username)
            .onStart { updateState { copy(isLoading = true) } }
            .collect { result ->
                with(result) {
                    onSuccess { sendEvent(UiEvent.NavigateToHome) }
                    onFailure {
                        updateState { copy(isLoading = false) }
                        sendEvent(UiEvent.Error(it.message ?: "Unknown error"))
                    }
                }
            }
    }

    fun updateName(name: String) {
        updateState { copy(name = name) }
    }

    fun updateUsername(username: String) {
        updateState { copy(username = username) }
    }
}

sealed interface UiEvent {

    data class Error(val message: String) : UiEvent

    data object NavigateToHome : UiEvent
}

data class RegState(
    val name: String = "",
    val username: String = "",
    val isLoading: Boolean = false,
)