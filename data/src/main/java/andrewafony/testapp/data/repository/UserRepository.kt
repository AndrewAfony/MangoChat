package andrewafony.testapp.data.repository

import andrewafony.testapp.shared_data.BaseRepository
import andrewafony.testapp.data.local.UserDao
import andrewafony.testapp.data.remote.model.request.Avatar
import andrewafony.testapp.data.remote.model.request.UserRequest
import andrewafony.testapp.data.remote.model.request.toRequestDate
import andrewafony.testapp.data.remote.service.UserService
import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserField
import andrewafony.testapp.domain.repository.UserRepository
import andrewafony.testapp.shared_data.utils.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userService: UserService,
    private val imageHandler: ImageHandler,
) : BaseRepository(), UserRepository {

    override fun user(): Flow<Result<User>> = flow {

        val user = userDao.observeUser().firstOrNull()

        if (user == null) {
            doNetworkRequestUnitWithCollect {
                userService.userInfo().onSuccess {
                    userDao.saveUserInfo(it.toEntity())
                }
            }
        }

        emitAll(userDao.observeUser()
            .filterNotNull()
            .map { Result.success(it.toDomain()) }
        )
    }

    override suspend fun userInfo(): User = doLocalRequest { userDao.userInfo() }

    override suspend fun updateUserInfo(field: UserField): Unit = withContext(Dispatchers.IO) {
        val user = userDao.userInfo()
        when (field) {
            is UserField.Name -> {
                userDao.updateUserName(field.name)
                doNetworkRequestUnitWithCollect {
                    userService.updateUserInfo(
                        UserRequest(
                            name = field.name,
                            username = user.username
                        )
                    )
                }
            }

            is UserField.About -> {
                userDao.updateUserAbout(field.about)
                doNetworkRequestUnitWithCollect {
                    userService.updateUserInfo(
                        UserRequest(
                            name = user.name,
                            username = user.username,
                            status = field.about
                        )
                    )
                }
            }

            is UserField.Birthday -> {
                userDao.updateUserBirthday(field.birthday)
                doNetworkRequestUnitWithCollect {
                    userService.updateUserInfo(
                        UserRequest(
                            name = user.name,
                            username = user.username,
                            birthday = field.birthday.toRequestDate()
                        )
                    )
                }
            }

            is UserField.City -> {
                userDao.updateUserCity(field.city)
                doNetworkRequestUnitWithCollect {
                    userService.updateUserInfo(
                        UserRequest(
                            name = user.name,
                            username = user.username,
                            city = field.city
                        )
                    )
                }
            }

            is UserField.Image -> {
                val imageUri = imageHandler.saveImageLocally(field.image)
                userDao.updateUserImage(imageUri)
                imageHandler.encode(field.image)?.let { encodedImage ->
                    doNetworkRequestUnitWithCollect {
                        userService.updateUserInfo(
                            UserRequest(
                                name = user.name,
                                username = user.username,
                                avatar = Avatar(
                                    base_64 = encodedImage,
                                    "user_${user.id}_profile_image"
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}