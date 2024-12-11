package andrewafony.testapp.data.local.entities

import andrewafony.testapp.data.utils.toZodiac
import andrewafony.testapp.domain.model.User
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class UserEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val username: String,
    val phone: String,
    val avatar: Uri?,
    val birthday: LocalDate?,
    val city: String?,
    val last: String?,
    val status: String?,
    val created: String?,
    val zodiac: String?,
)

/**
 * Get from database
 */
fun UserEntity.asUser() = User(
    name = name,
    username = username,
    image = avatar ?: Uri.EMPTY,
    phone = phone,
    status = last ?: "",
    birthday = birthday,
    city = city ?: "",
    zodiac = birthday?.toZodiac() ?: "",
    about = status ?: ""
)

/**
 * Save to database
 */
fun User.asEntity() = UserEntity(
    id = 0,
    name = name,
    username = username,
    avatar = image,
    birthday = birthday,
    city = city,
    last = status,
    status = about,
    phone = phone,
    created = "",
    zodiac = zodiac
)