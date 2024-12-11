package andrewafony.testapp.domain.model

import android.net.Uri
import java.time.LocalDate

data class User(
    val name: String,
    val surname: String,
    val username: String,
    val image: Uri,
    val phone: String,
    val status: String,
    val birthday: LocalDate?,
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
            birthday = LocalDate.of(2024, 5, 5),
            city = "",
            zodiac = "",
            about = ""
        )
    }
}