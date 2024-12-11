package andrewafony.testapp.domain.repository

import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun userInfo() : Flow<Result<User>>

    suspend fun updateUserInfo(user: User)
}