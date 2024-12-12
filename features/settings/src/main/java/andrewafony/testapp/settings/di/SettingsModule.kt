package andrewafony.testapp.settings.di

import andrewafony.testapp.settings.navigation.SettingsFeatureImpl
import andrewafony.testapp.settings_api.SettingsFeatureApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import andrewafony.testapp.settings.SettingsViewModel

val settingsModule = module {

    single<SettingsFeatureApi> { SettingsFeatureImpl(get()) }

    viewModelOf(::SettingsViewModel)
}