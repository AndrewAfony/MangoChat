package andrewafony.testapp.data.repository

import andrewafony.testapp.data.utils.Token
import andrewafony.testapp.data.utils.TokenManager
import andrewafony.testapp.data.remote.model.request.Auth
import andrewafony.testapp.data.remote.model.request.AuthCode
import andrewafony.testapp.data.remote.model.request.Registration
import andrewafony.testapp.data.remote.service.AuthService
import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.repository.AuthRepository
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {

    override fun sendAuthCode(phone: String): Flow<Boolean> = flow {
        val response = authService.auth(Auth(phone))
        emit(response.isSuccessful && response.body()?.isSuccess ?: false)
    }
        .flowOn(ioDispatcher)
        .catch { error ->
            Timber.e(error)
            emit(false)
        }

    override fun checkAuthCode(phone: String, code: String): Flow<Result<Boolean>> = flow<Result<Boolean>> {
        val response = authService.checkAuthCode(AuthCode(phone, code))
        with(tokenManager) {
            saveToken(Token.Access(response.accessToken ?: ""))
            saveToken(Token.Refresh(response.refreshToken ?: ""))
        }
        emit(Result.Success(response.isUserExists ?: false))
    }
        .flowOn(ioDispatcher)
        .catch { error ->
            Timber.e(error)
            emit(Result.Error(error.localizedMessage ?: "Unknown error"))
        }

    override fun register(phone: String, name: String, username: String): Flow<Result<Boolean>> = flow<Result<Boolean>> {
        val response = authService.register(Registration(phone, name, username))
        with(tokenManager) {
            saveToken(Token.Access(response.accessToken ?: ""))
            saveToken(Token.Refresh(response.refreshToken ?: ""))
        }
        emit(Result.Success(response.isUserExists ?: false))
    }
        .flowOn(ioDispatcher)
        .catch { error ->
            Timber.e(error)
            emit(Result.Error(error.localizedMessage ?: "Unknown error"))
        }
}


