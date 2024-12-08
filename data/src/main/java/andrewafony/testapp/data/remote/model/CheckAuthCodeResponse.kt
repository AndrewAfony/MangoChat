package andrewafony.testapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("user_id")val userId: Int,
    @SerialName("is_user_exists") val isUserExists: Boolean? = null,
)