package andrewafony.testapp.domain.model

import android.net.Uri

data class User(
    val name: String,
    val surname: String,
    val username: String,
    val image: Uri,
    val phone: String,
    val status: String,
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
            status = "Online",
            phone = "+7 (952) 773-56-92",
            birthday = Birthday("2001", Month("Май", "05"), "24"),
            city = "Нижний Новгород",
            zodiac = "Близнецы",
            about = ""
        )
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