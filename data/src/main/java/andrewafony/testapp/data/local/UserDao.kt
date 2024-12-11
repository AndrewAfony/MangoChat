package andrewafony.testapp.data.local

import andrewafony.testapp.data.local.entities.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity WHERE id = 0")
    suspend fun userInfo(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserInfo(userEntity: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM userentity WHERE id = 0)")
    suspend fun exists(): Boolean
}