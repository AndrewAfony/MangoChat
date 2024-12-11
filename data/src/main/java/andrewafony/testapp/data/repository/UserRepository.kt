package andrewafony.testapp.data.repository

import andrewafony.testapp.data.local.UserDao
import andrewafony.testapp.data.local.entities.asUser
import andrewafony.testapp.data.remote.model.response.asEntity
import andrewafony.testapp.data.remote.model.response.asUser
import andrewafony.testapp.data.remote.service.MainService
import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserField
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
    private val imageHandler: ImageHandler
): UserRepository {

    override fun userInfo(): Flow<Result<User>> = channelFlow {
        if (userDao.exists()) {
            send(Result.Success(userDao.userInfo().asUser()))
        } else {
            try {
                val response = mainService.userInfo()
                send(Result.Success(response.asUser()))
                launch { userDao.saveUserInfo(response.asEntity()) }
            } catch (e: Exception) {
                Log.d("MyHelper", "userInfo error: ${e.localizedMessage}")
                send(Result.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserInfo(field: UserField): Unit = withContext(Dispatchers.IO) {
        launch {
            when (field) {
                is UserField.Name -> userDao.updateUserName(field.name)
                is UserField.Birthday -> userDao.updateUserBirthday(field.birthday)
                is UserField.City -> userDao.updateUserCity(field.city)
                is UserField.Image -> {
                    val imageUri = imageHandler.saveImageLocally(field.image)
                    userDao.updateUserImage(imageUri)
                }
            }
            launch {
                val user = userDao.userInfo()
                val encodedImage = imageHandler.encode(user.avatar)
                try {
//                mainService.updateUserInfo(user.asRequest(encodedImage))
                } catch (e: Exception) {
                    Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
                }
            }
        }
    }
}