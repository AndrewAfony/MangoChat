package andrewafony.testapp.data.di

import andrewafony.testapp.data.remote.CloudDataSource
import andrewafony.testapp.data.repository.AuthRepositoryImpl
import andrewafony.testapp.data.repository.ChatRepositoryImpl
import andrewafony.testapp.domain.repository.AuthRepository
import andrewafony.testapp.domain.repository.ChatRepository
import org.koin.dsl.module

val dataModule = module {

    // TODO single/factory?

    includes(networkModule)

    single { CloudDataSource(get()) }

    single<ChatRepository> { ChatRepositoryImpl() }

    single<AuthRepository> { AuthRepositoryImpl(get()) }
}