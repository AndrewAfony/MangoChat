package andrewafony.testapp.settings.di

import andrewafony.testapp.settings.navigation.SettingsFeatureImpl
import andrewafony.testapp.settings_api.SettingsFeatureApi
import org.koin.dsl.module

val settingsModule = module {

    single<SettingsFeatureApi> { SettingsFeatureImpl(get()) }
}