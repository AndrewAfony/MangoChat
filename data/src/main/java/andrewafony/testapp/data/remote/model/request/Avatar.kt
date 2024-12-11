package andrewafony.testapp.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    val base_64: String,
    val filename: String
)