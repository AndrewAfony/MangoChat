package andrewafony.testapp.data.remote.model.request

import andrewafony.testapp.domain.model.User
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserRequest(
    val name: String = "",
    val username: String = "",
    val avatar: Avatar = Avatar("", ""),
    val birthday: String = "",
    val city: String = "",
    val instagram: String = "",
    val status: String = "",
    val vk: String = ""
)

fun LocalDate.toRequestDate() = "${year}-${monthValue}-${dayOfMonth}"