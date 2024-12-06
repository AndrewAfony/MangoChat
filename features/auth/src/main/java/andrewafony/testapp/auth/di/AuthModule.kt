package andrewafony.testapp.auth.di

import andrewafony.testapp.auth.navigation.AuthFeatureImpl
import andrewafony.testapp.auth_api.AuthFeatureApi
import org.koin.dsl.module

val authModule = module {

    single<AuthFeatureApi> { AuthFeatureImpl() }
}