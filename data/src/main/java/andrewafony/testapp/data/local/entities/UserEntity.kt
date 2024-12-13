package andrewafony.testapp.data.local.entities

import andrewafony.testapp.common.utils.toZodiac
import andrewafony.testapp.domain.DataMapper
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
): DataMapper<User> {

    override fun toDomain(): User = User(
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
}