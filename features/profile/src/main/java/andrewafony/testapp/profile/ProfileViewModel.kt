package andrewafony.testapp.profile

import andrewafony.testapp.domain.model.Birthday
import andrewafony.testapp.domain.model.User
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _userState = MutableStateFlow<User>(User.empty())
    val userState: StateFlow<User> = _userState

    fun updateImage(image: Uri?) {
        image?.let { photo ->
            _userState.update { it.copy(image = photo) }
        }
    }

    fun updateBirthday(birthday: Birthday) {
        _userState.update { it.copy(birthday = birthday) }
    }

    fun updateName(name: String, surname: String) {
        _userState.update { it.copy(name = name, surname = surname) }
    }

    fun updateCity(city: String) {
        _userState.update { it.copy(city = city) }
    }
}