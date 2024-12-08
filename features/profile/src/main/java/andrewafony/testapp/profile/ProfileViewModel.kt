package andrewafony.testapp.profile

import andrewafony.testapp.domain.model.User
import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class ProfileViewModel : ViewModel() {

    private val _userState = MutableStateFlow<User>(User.empty())
    val userState: StateFlow<User> = _userState

    fun updateImage(image: Uri?) {
        image?.let { photo -> // TODO image to base64
            _userState.update { it.copy(image = image.toString()) }
        }
    }

    fun updateBirthday(birthday: LocalDate) {
        _userState.update { it.copy(birthday = birthday) }
    }

    fun updateName(name: String, surname: String) {
        _userState.update { it.copy(name = name, surname = surname) }
    }

    fun updateCity(city: String) {
        _userState.update { it.copy(city = city) }
    }
}