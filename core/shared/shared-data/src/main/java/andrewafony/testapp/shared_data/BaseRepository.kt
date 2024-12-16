package andrewafony.testapp.shared_data

import andrewafony.testapp.domain.DataMapper
import andrewafony.testapp.domain.model.error.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import retrofit2.Response
import timber.log.Timber
import java.io.InterruptedIOException

abstract class BaseRepository {

    protected suspend fun <T> doNetworkRequestUnitWithCollect(
        request: suspend () -> Response<T>,
    ) : Result<Unit> = doRequest(request) { Unit }.first()

    protected fun <T> doNetworkRequest(
        request: suspend () -> Response<T>,
    ): Flow<Result<T>> = doRequest(request) { response -> response }

    protected fun <D : DataMapper<T>, T> doNetworkRequestWithMapping(
        request: suspend () -> Response<D>,
    ): Flow<Result<T>> = doRequest(request) { response -> response.toDomain() }

    protected fun <T> doNetworkRequestUnit(
        request: suspend () -> Response<T>,
    ): Flow<Result<Unit>> = doRequest(request) { Unit }

    protected fun <D : DataMapper<T>, T> doLocalObserveRequest(
        request: () -> Flow<D>,
    ): Flow<T> = request().map { it.toDomain() }

    protected suspend fun <D : DataMapper<T>, T> doLocalRequest(
        request: suspend () -> D,
    ): T = request().toDomain()

    private fun <T, S> doRequest(
        request: suspend () -> Response<T>,
        onSuccess: (T) -> S,
    ) = flow<Result<S>> {
        request().let { response ->
            when {
                response.isSuccessful -> {
                    response.body()?.let { body ->
                        emit(Result.success(onSuccess.invoke(body)))
                    }
                }

                !response.isSuccessful && response.code() == 422 -> {
                    emit(
                        Result.failure(
                            NetworkError.ApiInputs(
                                Json.decodeFromString(
                                    response.errorBody().toString()
                                )
                            )
                        )
                    )
                }

                else -> {
                    emit(
                        Result.failure(
                            NetworkError.Api(
                                Json.decodeFromString(
                                    response.errorBody().toString()
                                )
                            )
                        )
                    )
                }
            }
        }
    }
        .flowOn(Dispatchers.IO)
        .catch { exception ->
            when (exception) {
                is InterruptedIOException -> {
                    emit(Result.failure(NetworkError.Timeout))
                }

                else -> {
                    val message = exception.localizedMessage ?: "Error Occurred!"
                    Timber.e(exception)
                    emit(Result.failure(NetworkError.Unexpected(message)))
                }
            }
        }
}