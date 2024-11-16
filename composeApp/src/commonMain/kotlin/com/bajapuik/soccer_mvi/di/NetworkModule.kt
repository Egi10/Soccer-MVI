package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.core.common.Platform
import com.bajapuik.soccer_mvi.core.network.HttpClientFactory
import com.bajapuik.soccer_mvi.core.network.JsonFactory
import org.koin.dsl.module

val networkModule = module {
    single {
        JsonFactory.create()
    }

    single {
        HttpClientFactory.create(
            json = get(),
            baseUrl = "thesportsdb.com",
            apiVersion = "api/v1/json/3/",
            isDebugMode = Platform.isDebug
        )
    }
}