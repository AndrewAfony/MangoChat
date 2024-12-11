package andrewafony.testapp.data.local

import andrewafony.testapp.data.local.entities.UserEntity
import android.net.Uri
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity WHERE id = 0")
    fun currentUser() : Flow<UserEntity>

    @Query("SELECT * FROM userentity WHERE id = :userId")
    suspend fun userInfo(userId: Int = 0): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserInfo(userEntity: UserEntity)

    @Query("UPDATE userentity SET city=:city WHERE id = :userId")
    suspend fun updateUserCity(city: String, userId: Int = 0)

    @Query("UPDATE userentity SET name=:name WHERE id = :userId")
    suspend fun updateUserName(name: String, userId: Int = 0)

    @Query("UPDATE userentity SET birthday=:birthday WHERE id = :userId")
    suspend fun updateUserBirthday(birthday: LocalDate, userId: Int = 0)

    @Query("UPDATE userentity SET avatar=:image WHERE id = :userId")
    suspend fun updateUserImage(image: Uri?, userId: Int = 0)

    @Query("SELECT EXISTS(SELECT * FROM userentity WHERE id = :userId)")
    suspend fun exists(userId: Int = 0): Boolean
}