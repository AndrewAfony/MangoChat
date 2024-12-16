package andrewafony.testapp.profile

import andrewafony.testapp.shared_ui.utils.RestartableStateFlow
import andrewafony.testapp.shared_ui.utils.restartableStateIn
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserField
import andrewafony.testapp.domain.repository.UserRepository
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val state: RestartableStateFlow<ProfileState> = userRepository.user()
        .map { result ->
            if (result.isSuccess) {
                ProfileState(screenState = ProfileScreenState.Success, user = result.getOrThrow())
            } else
                ProfileState(screenState = ProfileScreenState.Error)
        }
        .restartableStateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ProfileState(screenState = ProfileScreenState.Loading)
        )

    fun updateImage(image: Uri?) {
        viewModelScope.launch {
            image?.let { photo ->
                userRepository.updateUserInfo(UserField.Image(photo))
            }
        }
    }

    fun updateBirthday(birthday: LocalDate) {
        viewModelScope.launch {
            userRepository.updateUserInfo(UserField.Birthday(birthday))
        }
    }

    fun updateName(name: String) {
        viewModelScope.launch {
            if (name.isNotBlank()) {
                userRepository.updateUserInfo(UserField.Name(name))
            }
        }
    }

    fun updateCity(city: String) {
        viewModelScope.launch {
            if (city.isNotBlank()) {
                userRepository.updateUserInfo(UserField.City(city))
            }
        }
    }

    fun updateAbout(about: String) {
        viewModelScope.launch {
            userRepository.updateUserInfo(UserField.About(about))
        }
    }
}

data class ProfileState(
    val screenState: ProfileScreenState = ProfileScreenState.Loading,
    val user: User? = null
)

sealed interface ProfileScreenState {

    data object Loading : ProfileScreenState

    data object Error : ProfileScreenState

    data object Success : ProfileScreenState
}