package andrewafony.testapp.data.di

import andrewafony.testapp.data.repository.ChatRepositoryImpl
import andrewafony.testapp.domain.repository.ChatRepository
import org.koin.dsl.module

val dataModule = module {

    includes(networkModule)

    single<ChatRepository> { ChatRepositoryImpl() }
}