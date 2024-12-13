package andrewafony.testapp.domain.model.error

sealed class NetworkError(message: String? = null) : Exception(message) {

    data object Timeout : NetworkError(message = "Timeout")

    data class ApiInputs(val error: ApiError) :
        NetworkError(message = error.detail.map { it.msg }.first())

    class Api(error: Error) : NetworkError(error.message)

    class Unexpected(message: String) : NetworkError(message)
}