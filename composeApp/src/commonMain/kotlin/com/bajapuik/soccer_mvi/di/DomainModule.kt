package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.domain.usecase.GetEventsLastUseCase
import com.bajapuik.soccer_mvi.domain.usecase.GetTopCountriesUseCase
import com.bajapuik.soccer_mvi.domain.usecase.SearchAllTeamByCountryUseCase
import com.bajapuik.soccer_mvi.domain.utils.NetworkBoundResource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory {
        NetworkBoundResource(
            dispatcher = get(qualifier = named(DISPATCHER_IO))
        )
    }
    factoryOf(::GetTopCountriesUseCase)
    factoryOf(::SearchAllTeamByCountryUseCase)
    factoryOf(::GetEventsLastUseCase)
}