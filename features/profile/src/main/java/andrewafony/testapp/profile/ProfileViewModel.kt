package andrewafony.testapp.profile

import andrewafony.testapp.designsystem.restartableStateIn
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

    val profileState = userRepository.userInfo()
        .map { result ->
            if (result is Result.Success)
                ProfileState.Success(result.data)
            else
                ProfileState.Error
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
                profileState.restart()
            }
        }
    }

    fun updateBirthday(birthday: LocalDate) {
        viewModelScope.launch {
            userRepository.updateUserInfo(UserField.Birthday(birthday))
            profileState.restart()
        }
    }

    fun updateName(name: String, surname: String) {
        viewModelScope.launch {
            userRepository.updateUserInfo(UserField.Name("$name $surname"))
            profileState.restart()
        }
    }

    fun updateCity(city: String) {
        viewModelScope.launch {
            if (city.isNotBlank()) {
                userRepository.updateUserInfo(UserField.City(city))
                profileState.restart()
            }
        }
    }
}

sealed interface ProfileState {

    data object Loading : ProfileState

    data object Error : ProfileState

    data class Success(val user: User) : ProfileState
}