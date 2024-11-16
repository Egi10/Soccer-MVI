package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.core.database.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databaseDriverModule(): Module = module {
    single {
        DriverFactory()
    }
}