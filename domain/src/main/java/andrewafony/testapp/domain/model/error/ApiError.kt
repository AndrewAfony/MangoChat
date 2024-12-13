package andrewafony.testapp.domain.model.error

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val detail: List<Detail>
)

@Serializable
data class Detail(
    val loc: List<String>,
    val msg: String,
    val type: String
)