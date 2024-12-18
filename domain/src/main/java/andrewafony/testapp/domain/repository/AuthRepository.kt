package andrewafony.testapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun sendAuthCode(phone: String): Flow<Result<Boolean>>

    fun checkAuthCode(phone: String, code: String): Flow<Result<Boolean>>

    fun register(phone: String, name: String, username: String): Flow<Result<Boolean>>
}