package andrewafony.testapp.home.di

import andrewafony.testapp.home.navigation.HomeFeatureImpl
import andrewafony.testapp.home_api.HomeFeatureApi
import org.koin.dsl.module

val homeModule = module {

    single<HomeFeatureApi> { HomeFeatureImpl(get()) }
}