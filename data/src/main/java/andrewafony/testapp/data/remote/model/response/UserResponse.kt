package andrewafony.testapp.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val profile_data: ProfileData
)