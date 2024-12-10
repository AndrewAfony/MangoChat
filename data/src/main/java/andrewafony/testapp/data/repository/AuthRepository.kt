package andrewafony.testapp.data.repository

import andrewafony.testapp.data.remote.CloudDataSource
import andrewafony.testapp.data.remote.model.response.asAuthCode
import andrewafony.testapp.domain.model.AuthCode
import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.repository.AuthRepository
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val cloudDataSource: CloudDataSource,
) : AuthRepository {

    override suspend fun sendAuthCode(phone: String): Boolean = withContext(Dispatchers.IO) {
        try {
            cloudDataSource.auth(phone)
        } catch (e: Exception) {
            Log.d("MyHelper", "sendAuthCode: ${e.localizedMessage}")
            false
        }
    }

    override suspend fun checkAuthCode(phone: String, code: String): Result<AuthCode> =
        withContext(Dispatchers.IO) {
            try {
                val result = cloudDataSource.checkAuthCode(phone, code)
                Result.Success(result.asAuthCode())
            } catch (e: Exception) {
                Log.d("MyHelper", "checkAuthCode: ${e.localizedMessage}")
                Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }

    override suspend fun register(phone: String, name: String, username: String): Result<AuthCode> =
        withContext(Dispatchers.IO) {
            try {
                val result = cloudDataSource.register(phone, name, username)
                // TODO save tokens and pass only data that is needed
                Result.Success(result.asAuthCode())
            } catch (e: Exception) {
                Log.d("MyHelper", "register: ${e.localizedMessage}")
                Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }
}


