package andrewafony.testapp.data.remote.model.response

import andrewafony.testapp.domain.model.AuthCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeResponse(
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("is_user_exists") val isUserExists: Boolean? = null,
)

fun CheckAuthCodeResponse.asAuthCode() = AuthCode(
    accessToken ?: "",
    refreshToken ?: "",
    userId ?: -1,
    isUserExists ?: false
)