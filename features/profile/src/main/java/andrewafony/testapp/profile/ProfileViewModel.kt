package andrewafony.testapp.profile

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
        Log.d("MyHelper", "updateName: $name $surname | ${userState.value}")
    }

    fun updateCity(city: String) {
        _userState.update { it.copy(city = city) }
    }
}

data class Birthday(
    val year: String,
    val month: Month,
    val day: String,
)

data class Month(
    val name: String,
    val number: String,
)

data class User(
    val name: String,
    val surname: String,
    val username: String,
    val image: Uri,
    val phone: String,
    val birthday: Birthday,
    val city: String,
    val zodiac: String,
    val about: String,
) {

    companion object {
        fun empty() = User(
            name = "Andrew",
            surname = "Afanasiev",
            username = "@andrew_afony",
            image = Uri.EMPTY,
            phone = "+7 (952) 773-56-92",
            birthday = Birthday("2001", Month("Май", "05"), "24"),
            city = "Нижний Новгород",
            zodiac = "Близнецы",
            about = ""
        )
    }
}