package andrewafony.testapp.mangotestchat

import andrewafony.testapp.data.utils.TokenManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    fun isLogged(): Boolean = runBlocking {
        tokenManager.accessToken.first().isNotBlank() &&
        tokenManager.refreshToken.first().isNotBlank()
    }
}