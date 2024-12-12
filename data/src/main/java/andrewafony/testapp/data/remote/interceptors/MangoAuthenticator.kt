package andrewafony.testapp.data.remote.interceptors

import andrewafony.testapp.data.utils.Token
import andrewafony.testapp.data.utils.TokenManager
import andrewafony.testapp.data.remote.service.AuthService
import android.util.Log
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class MangoAuthenticator(
    private val tokenManager: TokenManager,
    private val authService: AuthService
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking { tokenManager.refreshToken.first() }
        return runBlocking {
            val newToken = authService.refreshToken(token)

            newToken.body()?.let {
                it.accessToken?.let { access -> tokenManager.saveToken(Token.Access(access)) }
                it.refreshToken?.let { refresh -> tokenManager.saveToken(Token.Refresh(refresh)) }
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }
}