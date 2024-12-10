package andrewafony.testapp.auth.di

import andrewafony.testapp.auth.navigation.AuthFeatureImpl
import andrewafony.testapp.auth_api.AuthFeatureApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import andrewafony.testapp.auth.login.AuthViewModel
import andrewafony.testapp.auth.registration.RegistrationViewModel

val authModule = module {

    single<AuthFeatureApi> { AuthFeatureImpl(get()) }

    viewModelOf(::AuthViewModel)

    viewModelOf(::RegistrationViewModel)
}