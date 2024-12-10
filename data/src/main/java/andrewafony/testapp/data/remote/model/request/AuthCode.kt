package andrewafony.testapp.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthCode(
    val phone: String,
    val code: String
)
