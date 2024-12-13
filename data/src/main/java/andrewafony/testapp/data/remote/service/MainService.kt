package andrewafony.testapp.data.remote.service

import andrewafony.testapp.data.remote.model.request.UserRequest
import andrewafony.testapp.data.remote.model.response.Avatars
import andrewafony.testapp.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface MainService {

    @GET("api/v1/users/me/")
    suspend fun userInfo() : Response<UserResponse>

    @PUT("api/v1/users/me")
    suspend fun updateUserInfo(@Body user: UserRequest) : Response<Avatars>
}