package andrewafony.testapp.profile

import andrewafony.testapp.common.utils.restartableStateIn
import andrewafony.testapp.domain.model.Result
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

    val userState = userRepository.user()
        .map { result ->
            if (result is Result.Success) {
                ProfileState.Success(result.data)
            } else {
                ProfileState.Error
            }
        }
        .restartableStateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ProfileState.Loading
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

sealed interface ProfileState {

    data object Loading : ProfileState

    data object Error : ProfileState

    data class Success(val user: User) : ProfileState
}