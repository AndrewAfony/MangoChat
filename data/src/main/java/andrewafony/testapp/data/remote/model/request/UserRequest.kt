package andrewafony.testapp.data.remote.model.request

import andrewafony.testapp.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val avatar: Avatar,
    val birthday: String,
    val city: String,
    val instagram: String,
    val name: String,
    val status: String,
    val username: String,
    val vk: String
)

fun User.asRequest(imageBase64: String?) = UserRequest(
    name = name,
    username = username,
    avatar = Avatar(
        base_64 = imageBase64 ?: "",
        filename = "${username}_profile_image"
    ),
    birthday = "${birthday?.year}-${birthday?.dayOfMonth}-${birthday?.monthValue}",
    city = city ?: "",
    instagram = "",
    vk = "",
    status = about
)