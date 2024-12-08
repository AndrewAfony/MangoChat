package andrewafony.testapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Avatars(
    val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String
)