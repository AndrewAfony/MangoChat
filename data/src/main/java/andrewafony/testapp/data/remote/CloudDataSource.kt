package andrewafony.testapp.data.remote

import andrewafony.testapp.data.remote.model.request.Auth
import andrewafony.testapp.data.remote.model.request.AuthCode
import andrewafony.testapp.data.remote.model.request.Registration
import andrewafony.testapp.data.remote.model.response.CheckAuthCodeResponse
import andrewafony.testapp.data.remote.service.AuthService

class CloudDataSource(
    private val authService: AuthService
) {

    suspend fun auth(phone: String): Boolean {
        val response = authService.auth(Auth(phone))
        return response.isSuccessful && response.body()?.isSuccess ?: false
    }

    suspend fun checkAuthCode(phone: String, code: String): CheckAuthCodeResponse =
        authService.checkAuthCode(AuthCode(phone, code))

    suspend fun register(phone: String, name: String, username: String) =
        authService.register(Registration(phone, name, username))
}