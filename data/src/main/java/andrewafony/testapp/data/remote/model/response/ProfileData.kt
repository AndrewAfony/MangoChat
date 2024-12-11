package andrewafony.testapp.data.remote.model.response

import andrewafony.testapp.domain.model.User
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ProfileData(
    val id: Int,
    val name: String,
    val username: String,
    val avatar: String?,
    val avatars: Avatars?,
    val birthday: String?,
    val city: String?,
    val last: String?,
    val online: Boolean?,
    val status: String?,
    val phone: String?,
    val created: String?,
    val completed_task: Int?,
    val instagram: String?,
    val vk: String?
)

fun ProfileData.asUser() = User(
    name = name.substringBefore(" "),
    surname = name.substringAfter(" "),
    username = username,
    image = avatar?.toUri() ?: Uri.EMPTY,
    phone = phone ?: "",
    status = last ?: "",
    birthday = if (birthday != null) LocalDate.parse(birthday) else null,
    city = city ?: "",
    zodiac = "",
    about = status ?: ""
)