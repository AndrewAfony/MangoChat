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
    fun observeUser() : Flow<UserEntity?>

    @Query("SELECT * FROM userentity WHERE id = 0")
    suspend fun userInfo(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserInfo(userEntity: UserEntity)

    @Query("UPDATE userentity SET city=:city WHERE id = 0")
    suspend fun updateUserCity(city: String)

    @Query("UPDATE userentity SET name=:name WHERE id = 0")
    suspend fun updateUserName(name: String)

    @Query("UPDATE userentity SET birthday=:birthday WHERE id = 0")
    suspend fun updateUserBirthday(birthday: LocalDate)

    @Query("UPDATE userentity SET avatar=:image WHERE id = 0")
    suspend fun updateUserImage(image: Uri?)

    @Query("UPDATE userentity SET status=:about WHERE id = 0")
    suspend fun updateUserAbout(about: String)

    @Query("SELECT name FROM userentity WHERE id = 0")
    suspend fun userName() : String
}