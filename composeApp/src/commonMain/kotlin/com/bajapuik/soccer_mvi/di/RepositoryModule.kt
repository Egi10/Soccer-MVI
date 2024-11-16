package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.data.repository.SoccerRepositoryImpl
import com.bajapuik.soccer_mvi.domain.repository.SoccerRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::SoccerRepositoryImpl) {
        bind<SoccerRepository>()
    }
}