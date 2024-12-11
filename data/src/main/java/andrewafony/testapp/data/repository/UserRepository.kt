package andrewafony.testapp.data.repository

import andrewafony.testapp.data.ImageEncoder
import andrewafony.testapp.data.local.UserDao
import andrewafony.testapp.data.local.entities.asEntity
import andrewafony.testapp.data.local.entities.asUser
import andrewafony.testapp.data.remote.model.request.asRequest
import andrewafony.testapp.data.remote.model.response.asUser
import andrewafony.testapp.data.remote.service.MainService
import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserRepository
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val mainService: MainService,
    private val imageEncoder: ImageEncoder
): UserRepository {

    override fun userInfo(): Flow<Result<User>> = channelFlow {
        if (userDao.exists()) {
            send(Result.Success(userDao.userInfo().asUser()))
        } else {
            try {
                val response = mainService.userInfo()
                send(Result.Success(response.asUser()))
                launch { userDao.saveUserInfo(response.asUser().asEntity()) }
            } catch (e: Exception) {
                Log.d("MyHelper", "userInfo error: ${e.localizedMessage}")
                send(Result.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserInfo(user: User): Unit = withContext(Dispatchers.IO) {
        launch {
            userDao.saveUserInfo(user.asEntity())
        }
        launch {
            val encodedImage = imageEncoder.encode(user.image)
            try {
//                mainService.updateUserInfo(user.asRequest(encodedImage))
            } catch (e: Exception) {
                Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
            }
        }
    }
}