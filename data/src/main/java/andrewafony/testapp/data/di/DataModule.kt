package andrewafony.testapp.data.di

import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.data.utils.TokenManager
import andrewafony.testapp.data.repository.AuthRepositoryImpl
import andrewafony.testapp.data.repository.ChatRepositoryImpl
import andrewafony.testapp.data.repository.TestUserRepository
import andrewafony.testapp.data.repository.UserRepositoryImpl
import andrewafony.testapp.domain.repository.AuthRepository
import andrewafony.testapp.domain.repository.ChatRepository
import andrewafony.testapp.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    // TODO single/factory?

    includes(networkModule, databaseModule)

    single<ChatRepository> { ChatRepositoryImpl() }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }

    single { TokenManager(androidContext()) }

    factory { ImageHandler(androidContext()) }

}