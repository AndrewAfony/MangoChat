package andrewafony.testapp.data.remote.model.response

import andrewafony.testapp.data.local.entities.UserEntity
import andrewafony.testapp.domain.DatabaseMapper
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val profile_data: ProfileData
) : DatabaseMapper<UserEntity> {

    override fun toEntity(): UserEntity = profile_data.toEntity()
}