package andrewafony.testapp.profile.di

import andrewafony.testapp.profile.ProfileViewModel
import andrewafony.testapp.profile.navigation.ProfileFeatureImpl
import andrewafony.testapp.profile_api.ProfileFeatureApi
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {

    viewModelOf(::ProfileViewModel)

    single<ProfileFeatureApi> { ProfileFeatureImpl() }
}