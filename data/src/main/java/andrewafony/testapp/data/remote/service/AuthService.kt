package andrewafony.testapp.data.remote.service

import andrewafony.testapp.data.remote.model.CheckAuthCodeResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("api/v1/users/send-auth-code/")
    fun auth(phone: String): Boolean

    @POST("api/v1/users/check-auth-code/")
    fun checkAuthCode(phone: String, code: String): CheckAuthCodeResponse

    @POST("api/v1/users/register/")
    fun register(phone: String, name: String, username: String): CheckAuthCodeResponse

    @POST("api/v1/users/refresh-token/")
    fun refreshToken(@Header("Authorization") token: String): Response<CheckAuthCodeResponse>
}