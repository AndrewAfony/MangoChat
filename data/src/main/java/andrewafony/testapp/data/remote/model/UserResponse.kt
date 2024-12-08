package andrewafony.testapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val profile_data: ProfileData
)