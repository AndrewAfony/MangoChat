package andrewafony.testapp.domain.use_cases

import andrewafony.testapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetUserInfoUseCase {

    operator fun invoke(): Flow<User> {
        return flowOf(User.empty())
    }
}