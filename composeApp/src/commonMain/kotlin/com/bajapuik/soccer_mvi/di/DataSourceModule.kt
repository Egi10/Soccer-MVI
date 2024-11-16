package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.data.datasource.local.LocalDataSource
import com.bajapuik.soccer_mvi.data.datasource.local.LocalDataSourceImpl
import com.bajapuik.soccer_mvi.data.datasource.remote.RemoteDataSource
import com.bajapuik.soccer_mvi.data.datasource.remote.RemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            httpClient = get(),
            dispatcher = get(named(DISPATCHER_IO))
        )
    }
    single<LocalDataSource> {
        LocalDataSourceImpl(
            teamQueries = get(),
            countryQueries = get(),
            dispatcher =  get(named(DISPATCHER_IO))
        )
    }
}