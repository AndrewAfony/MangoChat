package andrewafony.testapp.data.repository

import andrewafony.testapp.common.base.BaseRepository
import andrewafony.testapp.common.utils.onSuccess
import andrewafony.testapp.data.local.UserDao
import andrewafony.testapp.data.local.entities.UserEntity
import andrewafony.testapp.data.remote.model.request.Avatar
import andrewafony.testapp.data.remote.model.request.UserRequest
import andrewafony.testapp.data.remote.model.request.toRequestDate
import andrewafony.testapp.data.remote.service.MainService
import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.domain.DatabaseMapper
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserField
import andrewafony.testapp.domain.repository.UserRepository
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val mainService: MainService,
    private val imageHandler: ImageHandler,
) : BaseRepository(), UserRepository {

    override fun user(): Flow<Result<User>> = flow {

        val user = userDao.observeUser().firstOrNull()

        if (user != null)
            emit(Result.success(user.toDomain()))
        else {
            doNetworkRequestUnitWithCollect {
                mainService.userInfo().onSuccess {
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
                    mainService.updateUserInfo(
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
                    mainService.updateUserInfo(
                        UserRequest(
                            name = user.name,
                            username = user.username
                        )
                    )
                }
            }

            is UserField.Birthday -> {
                userDao.updateUserBirthday(field.birthday)
                doNetworkRequestUnitWithCollect {
                    mainService.updateUserInfo(
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
                    mainService.updateUserInfo(
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
                        mainService.updateUserInfo(
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