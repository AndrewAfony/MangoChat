package andrewafony.testapp.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val profile_data: ProfileData
)

fun UserResponse.asUser() = profile_data.asUser()

fun UserResponse.asEntity() = profile_data.asEntity()