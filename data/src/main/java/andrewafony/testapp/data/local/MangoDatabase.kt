package andrewafony.testapp.data.local

import andrewafony.testapp.data.local.entities.UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UserEntity::class],
    version = 1
)
@TypeConverters(MangoTypeConverters::class)
abstract class MangoDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}