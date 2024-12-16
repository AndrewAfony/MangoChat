package andrewafony.testapp.data.repository

import andrewafony.testapp.shared_data.BaseRepository
import andrewafony.testapp.data.remote.model.request.Auth
import andrewafony.testapp.data.remote.model.request.AuthCode
import andrewafony.testapp.data.remote.model.request.Registration
import andrewafony.testapp.data.remote.service.AuthService
import andrewafony.testapp.data.utils.Token
import andrewafony.testapp.data.utils.TokenManager
import andrewafony.testapp.domain.repository.AuthRepository
import andrewafony.testapp.shared_data.utils.onSuccess
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) : BaseRepository(), AuthRepository {

    override fun sendAuthCode(phone: String): Flow<Result<Boolean>> = doNetworkRequestWithMapping {
        authService.auth(Auth(phone))
    }

    override fun checkAuthCode(phone: String, code: String): Flow<Result<Boolean>> = doNetworkRequestWithMapping {
        authService.checkAuthCode(AuthCode(phone, code)).onSuccess {
            with(tokenManager) {
                saveToken(Token.Access(it.accessToken ?: ""))
                saveToken(Token.Refresh(it.refreshToken ?: ""))
            }
        }
    }

    override fun register(phone: String, name: String, username: String): Flow<Result<Boolean>> = doNetworkRequestWithMapping {
        authService.register(Registration(phone, name, username)).onSuccess {
            with(tokenManager) {
                saveToken(Token.Access(it.accessToken ?: ""))
                saveToken(Token.Refresh(it.refreshToken ?: ""))
            }
        }
    }
}


