package andrewafony.testapp.data.local.entities

import andrewafony.testapp.domain.model.User
import androidx.core.net.toUri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class UserEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val username: String,
    val avatar: String,
    val birthday: LocalDate,
    val city: String,
    val last: String,
    val online: Boolean,
    val status: String,
    val phone: String,
    val created: String,
    val zodiac: String,
)

fun UserEntity.asUser() = User(
    name = name.substringBefore(" "),
    surname = name.substringAfter(" "),
    username = username,
    image = avatar.toUri(),
    phone = phone,
    status = last,
    birthday = birthday,
    city = city,
    zodiac = zodiac,
    about = status
)

fun User.asEntity() = UserEntity(
    id = 0,
    name = "$name $surname",
    username = username,
    avatar = image.toString(),
    birthday = birthday ?: LocalDate.now(),
    city = city ?: "",
    last = "",
    online = false,
    status = about,
    phone = phone,
    created = "",
    zodiac = zodiac ?: ""
)