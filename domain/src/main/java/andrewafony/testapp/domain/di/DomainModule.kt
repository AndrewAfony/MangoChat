package andrewafony.testapp.domain.di

import andrewafony.testapp.domain.use_cases.GetUserInfoUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {

    singleOf(::GetUserInfoUseCase)
}