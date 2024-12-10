package andrewafony.testapp.data.remote.service

import andrewafony.testapp.data.remote.model.request.Auth
import andrewafony.testapp.data.remote.model.request.AuthCode
import andrewafony.testapp.data.remote.model.request.Registration
import andrewafony.testapp.data.remote.model.response.CheckAuthCodeResponse
import andrewafony.testapp.data.remote.model.response.SendAuthCodeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("api/v1/users/send-auth-code/")
    suspend fun auth(@Body auth: Auth): Response<SendAuthCodeResponse>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body authCode: AuthCode): CheckAuthCodeResponse

    @POST("api/v1/users/register/")
    suspend fun register(@Body registration: Registration): CheckAuthCodeResponse

    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(@Header("Authorization") token: String): Response<CheckAuthCodeResponse>
}