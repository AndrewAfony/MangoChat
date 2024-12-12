package andrewafony.testapp.home.di

import andrewafony.testapp.home.navigation.HomeFeatureImpl
import andrewafony.testapp.home_api.HomeFeatureApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import andrewafony.testapp.home.HomeViewModel

val homeModule = module {

    single<HomeFeatureApi> { HomeFeatureImpl(get()) }

    viewModelOf(::HomeViewModel)
}