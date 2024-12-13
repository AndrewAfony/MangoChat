package andrewafony.testapp.settings

import andrewafony.testapp.domain.repository.UserRepository
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val settingsState: StateFlow<SettingsState> = userRepository.user()
        .map { result ->
            if (result.isSuccess) {
                SettingsState.Success(
                    image = result.getOrThrow().image,
                    name = result.getOrThrow().name,
                    username = result.getOrThrow().username
                )
            } else {
                SettingsState.Error
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SettingsState.Loading
        )
}

sealed interface SettingsState {

    data object Loading : SettingsState

    data object Error : SettingsState

    data class Success(
        val image: Uri?,
        val name: String,
        val username: String,
    ) : SettingsState
}