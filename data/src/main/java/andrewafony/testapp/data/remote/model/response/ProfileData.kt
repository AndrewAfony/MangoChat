package andrewafony.testapp.data.remote.model.response

import andrewafony.testapp.common.utils.toLocalDate
import andrewafony.testapp.common.utils.toZodiac
import andrewafony.testapp.data.local.entities.UserEntity
import andrewafony.testapp.domain.model.User
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    val id: Int,
    val name: String,
    val username: String,
    val phone: String,
    val avatar: String?,
    val avatars: Avatars?,
    val birthday: String?,
    val city: String?,
    val last: String?,
    val online: Boolean?,
    val status: String?,
    val created: String?,
    val completed_task: Int?,
    val instagram: String?,
    val vk: String?
)

fun ProfileData.asUser() = User(
    name = name,
    username = username,
    image = avatar?.toUri() ?: Uri.EMPTY,
    phone = phone.toPhoneMask(),
    status = last ?: "",
    birthday = birthday.toLocalDate(),
    city = city ?: "",
    zodiac = birthday?.let { it.toLocalDate()?.toZodiac() } ?: "",
    about = status ?: ""
)

fun ProfileData.asEntity(): UserEntity {

    val birthday = birthday.toLocalDate()

    return UserEntity(
        id = 0,
        name = name,
        username = username,
        avatar = avatar?.toUri(),
        birthday = birthday,
        city = city,
        last = last,
        status = status,
        phone = phone.toPhoneMask(),
        created = created,
        zodiac = birthday?.toZodiac()
    )
}

private fun String.toPhoneMask(): String {
    val str = StringBuilder()

    forEachIndexed { index, c ->
        with(str) {
            when(index) {
                0 -> append("+$c (")
                3 -> append("$c) ")
                6 -> append("$c-")
                8 -> append("$c-")
                else -> append(c)
            }
        }
    }

    return str.toString()
}