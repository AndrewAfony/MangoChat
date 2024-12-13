package andrewafony.testapp.domain.model.error

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val message: String
)