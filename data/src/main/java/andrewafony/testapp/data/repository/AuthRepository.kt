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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun sendAuthCode(phone: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = authService.auth(Auth(phone))
            response.isSuccessful && response.body()?.isSuccess ?: false
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

    override suspend fun checkAuthCode(phone: String, code: String): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val response = authService.checkAuthCode(AuthCode(phone, code))
                with(tokenManager) {
                    saveToken(Token.Access(response.accessToken?:""))
                    saveToken(Token.Refresh(response.refreshToken?:""))
                }
                Result.Success(response.isUserExists ?: false)
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }

    override suspend fun register(phone: String, name: String, username: String): Result<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val response = authService.register(Registration(phone, name, username))
                with(tokenManager) {
                    saveToken(Token.Access(response.accessToken?:""))
                    saveToken(Token.Refresh(response.refreshToken?:""))
                }
                Result.Success(response.isUserExists ?: false)
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }
}


