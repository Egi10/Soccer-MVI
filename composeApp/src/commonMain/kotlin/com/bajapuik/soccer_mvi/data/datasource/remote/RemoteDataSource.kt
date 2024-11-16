package com.bajapuik.soccer_mvi.data.datasource.remote

import com.bajapuik.soccer_mvi.data.datasource.remote.response.CountriesResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.LastEventsResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.TeamsResponse

interface RemoteDataSource {
    suspend fun getAllCountries(): CountriesResponse
    suspend fun getAllTeamsByCountry(country: String): TeamsResponse
    suspend fun getLastEventByTeam(idTeam: String): LastEventsResponse
}