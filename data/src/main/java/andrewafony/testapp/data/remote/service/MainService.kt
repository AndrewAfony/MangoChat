package andrewafony.testapp.data.remote.service

import retrofit2.http.GET
import retrofit2.http.PUT

interface MainService {

    @GET("api/v1/users/me/")
    suspend fun userInfo()

    @PUT("api/v1/users/me")
    suspend fun updateUserInfo()
}