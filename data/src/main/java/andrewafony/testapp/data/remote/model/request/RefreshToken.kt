package andrewafony.testapp.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    val refreshToken: String
)
