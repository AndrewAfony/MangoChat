package andrewafony.testapp.data.remote.model.response

import andrewafony.testapp.domain.DataMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendAuthCodeResponse(
    @SerialName("is_success") val isSuccess: Boolean
) : DataMapper<Boolean> {

    override fun toDomain(): Boolean = isSuccess
}