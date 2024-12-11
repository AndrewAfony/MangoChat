package andrewafony.testapp.data.di

import andrewafony.testapp.data.local.MangoDatabase
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            MangoDatabase::class.java,
            "mango_db"
        ).build()
    }

    single { get<MangoDatabase>().userDao }
}