package andrewafony.testapp.domain.repository

import andrewafony.testapp.domain.model.Result
import andrewafony.testapp.domain.model.User
import android.net.Uri
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface UserRepository {

    fun user() : Flow<User>

    fun userInfo() : Flow<Result<Boolean>>

    suspend fun updateUserInfo(field: UserField)
}

sealed interface UserField {

    @JvmInline
    value class Name(val name: String) : UserField

    @JvmInline
    value class Birthday(val birthday: LocalDate) : UserField

    @JvmInline
    value class City(val city: String) : UserField

    @JvmInline
    value class Image(val image: Uri) : UserField
}