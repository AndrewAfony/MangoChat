package andrewafony.testapp.profile.di

import andrewafony.testapp.profile.navigation.ProfileFeatureImpl
import andrewafony.testapp.profile_api.ProfileFeatureApi
import org.koin.dsl.module

val profileModule = module {

    single<ProfileFeatureApi> { ProfileFeatureImpl() }
}