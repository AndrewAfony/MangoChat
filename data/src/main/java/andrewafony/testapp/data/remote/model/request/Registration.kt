package andrewafony.testapp.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    val phone: String,
    val name: String,
    val username: String,
)
