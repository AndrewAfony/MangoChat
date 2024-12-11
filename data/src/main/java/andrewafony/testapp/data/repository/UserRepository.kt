package andrewafony.testapp.data.repository

import andrewafony.testapp.data.local.UserDao
import andrewafony.testapp.data.local.entities.UserEntity
import andrewafony.testapp.data.local.entities.asUser
import andrewafony.testapp.data.remote.model.request.Avatar
import andrewafony.testapp.data.remote.model.request.UserRequest
import andrewafony.testapp.data.remote.model.request.toRequestDate
import andrewafony.testapp.data.remote.model.response.asEntity
import andrewafony.testapp.data.remote.service.MainService
import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserField
import andrewafony.testapp.domain.repository.UserRepository
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val mainService: MainService,
    private val imageHandler: ImageHandler,
) : UserRepository {

    override fun user(): Flow<User> = userDao.currentUser()
        .map(UserEntity::asUser)

    override fun userInfo(): Flow<Result<Boolean>> = flow {
        if (userDao.exists()) {
            emit(Result.Success(true))
        } else {
            try {
                val response = mainService.userInfo()
                userDao.saveUserInfo(response.asEntity())
                emit(Result.Success(true))
            } catch (e: Exception) {
                Log.d("MyHelper", "userInfo error: ${e.localizedMessage}")
                emit(Result.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserInfo(field: UserField): Unit = withContext(Dispatchers.IO) {
        val user = userDao.userInfo()
        when (field) {
            is UserField.Name -> {
                userDao.updateUserName(field.name)
                launch {
                    try {
                        mainService.updateUserInfo(UserRequest(name = field.name, username = user.username))
                    } catch (e: Exception) {
                        Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
                    }
                }
            }

            is UserField.Birthday -> {
                userDao.updateUserBirthday(field.birthday)
                launch {
                    try {
                        mainService.updateUserInfo(UserRequest(
                            name = user.name,
                            username = user.username,
                            birthday = field.birthday.toRequestDate()
                        ))
                    } catch (e: Exception) {
                        Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
                    }
                }
            }

            is UserField.City -> {
                userDao.updateUserCity(field.city)
                launch {
                    try {
                        mainService.updateUserInfo(UserRequest(
                            name = user.name,
                            username = user.username,
                            city = field.city)
                        )
                    } catch (e: Exception) {
                        Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
                    }
                }
            }

            is UserField.Image -> {
                val imageUri = imageHandler.saveImageLocally(field.image)
                userDao.updateUserImage(imageUri)
                launch {
                    try {
                        imageHandler.encode(field.image)?.let {
                            mainService.updateUserInfo(
                                UserRequest(
                                    name = user.name,
                                    username = user.username,
                                    avatar = Avatar(base_64 = it, "user_${user.id}_profile_image")
                                )
                            )
                        }
                    } catch (e: Exception) {
                        Log.d("MyHelper", "updateUserInfoError: ${e.localizedMessage}")
                    }
                }
            }
        }
    }
}