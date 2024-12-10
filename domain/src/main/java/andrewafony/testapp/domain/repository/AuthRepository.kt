package andrewafony.testapp.domain.repository

import andrewafony.testapp.domain.model.AuthCode
import andrewafony.testapp.domain.model.Result

interface AuthRepository {

    suspend fun sendAuthCode(phone: String): Boolean

    suspend fun checkAuthCode(phone: String, code: String): Result<AuthCode>

    suspend fun register(phone: String, name: String, username: String): Result<AuthCode>
}