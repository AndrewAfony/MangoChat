package andrewafony.testapp.domain.model

sealed interface Result<out T> {

    data class Error(val message: String) :
        Result<Nothing>

    data class Success<T>(val data: T) :
        Result<T>
}