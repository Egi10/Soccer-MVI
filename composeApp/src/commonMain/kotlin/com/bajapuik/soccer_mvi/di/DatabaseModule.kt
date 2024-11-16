package com.bajapuik.soccer_mvi.di

import com.bajapuik.soccer_mvi.core.database.DatabaseFactory
import com.bajapuik.soccer_mvi.db.SoccerDatabase
import com.bajapuik.soccermvi.db.CountryQueries
import com.bajapuik.soccermvi.db.TeamQueries
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect fun databaseDriverModule(): Module

val databaseModule = module {
    includes(databaseDriverModule())

    single {
        DatabaseFactory.create(
            driverFactory = get()
        )
    }

    singleOf(::provideTeamQueries)
    singleOf(::provideCountryQueries)
}

fun provideTeamQueries(soccerDatabase: SoccerDatabase): TeamQueries {
    return soccerDatabase.teamQueries
}

fun provideCountryQueries(soccerDatabase: SoccerDatabase): CountryQueries {
    return soccerDatabase.countryQueries
}