package andrewafony.testapp.chat.di

import andrewafony.testapp.chat.navigation.ChatFeatureImpl
import andrewafony.testapp.chat_api.ChatFeatureApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import andrewafony.testapp.chat.ChatViewModel

val chatModule = module {

    viewModelOf(::ChatViewModel)

    single<ChatFeatureApi> { ChatFeatureImpl() }
}