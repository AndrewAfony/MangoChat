package andrewafony.testapp.data.di

import andrewafony.testapp.data.cache.UserCache
import andrewafony.testapp.data.utils.ImageHandler
import andrewafony.testapp.data.utils.TokenManager
import andrewafony.testapp.data.repository.AuthRepositoryImpl
import andrewafony.testapp.data.repository.ChatRepositoryTest
import andrewafony.testapp.data.repository.ChatsRepositoryImpl
import andrewafony.testapp.data.repository.UserRepositoryImpl
import andrewafony.testapp.domain.repository.AuthRepository
import andrewafony.testapp.domain.repository.ChatRepository
import andrewafony.testapp.domain.repository.ChatsRepository
import andrewafony.testapp.domain.repository.UserPrefetch
import andrewafony.testapp.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    includes(networkModule, databaseModule)

    single<ChatRepository> { ChatRepositoryTest(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }

    single<UserPrefetch> { UserRepositoryImpl(get(), get(), get(), get()) }

    single { UserCache() }

    single<ChatsRepository> { ChatsRepositoryImpl() }

    single { TokenManager(androidContext()) }

    factory { ImageHandler(androidContext()) }

}