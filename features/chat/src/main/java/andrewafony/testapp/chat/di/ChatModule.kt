package andrewafony.testapp.chat.di

import andrewafony.testapp.chat.navigation.ChatFeatureImpl
import andrewafony.testapp.chat_api.ChatFeatureApi
import org.koin.dsl.module

val chatModule = module {

    single<ChatFeatureApi> { ChatFeatureImpl() }
}