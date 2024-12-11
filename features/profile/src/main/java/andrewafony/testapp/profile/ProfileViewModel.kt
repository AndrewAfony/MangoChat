package andrewafony.testapp.profile

import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import andrewafony.testapp.domain.repository.UserRepository
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val restarter = SharingStarted.WhileSubscribed(5000).makeRestartable()

    private val _userState = MutableStateFlow<User>(User.empty())
    val userState: StateFlow<User> = _userState

    val profileState: StateFlow<ProfileState> = userRepository.userInfo()
        .map { result ->
            if (result is Result.Success)
                ProfileState.Success(result.data)
            else
                ProfileState.Error
        }
        .stateIn(
            scope = viewModelScope,
            started = restarter,
            initialValue = ProfileState.Loading
        )

    fun updateImage(image: Uri?) {
        viewModelScope.launch {
            image?.let { photo ->
                userRepository.updateUserInfo(userState.value.copy(image = photo))
            }
        }
    }

    fun updateBirthday(birthday: LocalDate) {
        viewModelScope.launch {
            userRepository.updateUserInfo(userState.value.copy(birthday = birthday))
        }
    }

    fun updateName(name: String, surname: String) {
        viewModelScope.launch {
            userRepository.updateUserInfo(userState.value.copy(name = name, surname = surname))
        }
    }

    fun updateCity(city: String) {
        viewModelScope.launch {
            if (city.isNotBlank()) userRepository.updateUserInfo(userState.value.copy(city = city))
        }
    }

    fun retry() = restarter.restart()

}

sealed interface ProfileState {

    data object Loading : ProfileState

    data object Error : ProfileState

    data class Success(val user: User) : ProfileState
}

interface SharingRestartable: SharingStarted {
    fun restart()
}

private data class SharingRestartableImpl(
    private val sharingStarted: SharingStarted,
): SharingRestartable {

    private val restartFlow = MutableSharedFlow<SharingCommand>(extraBufferCapacity = 2)

    override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> {
        return merge(restartFlow, sharingStarted.command(subscriptionCount))
    }

    override fun restart() {
        restartFlow.tryEmit(SharingCommand.STOP_AND_RESET_REPLAY_CACHE)
        restartFlow.tryEmit(SharingCommand.START)
    }
}

fun SharingStarted.makeRestartable(): SharingRestartable {
    return SharingRestartableImpl(this)
}