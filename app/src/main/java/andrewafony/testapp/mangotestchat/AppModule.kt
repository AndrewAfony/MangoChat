package andrewafony.testapp.mangotestchat

import andrewafony.testapp.auth.di.authModule
import andrewafony.testapp.chat.di.chatModule
import andrewafony.testapp.home.di.homeModule
import andrewafony.testapp.profile.di.profileModule
import andrewafony.testapp.settings.di.settingsModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    includes(profileModule, homeModule, settingsModule, chatModule, authModule)

    singleOf(::FeatureDestinationProvider)
}