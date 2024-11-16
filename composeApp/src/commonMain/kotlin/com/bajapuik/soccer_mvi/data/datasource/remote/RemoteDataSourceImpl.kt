package com.bajapuik.soccer_mvi.data.datasource.remote

import com.bajapuik.soccer_mvi.data.datasource.remote.response.CountriesResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.LastEventsResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.TeamsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
): RemoteDataSource {
    override suspend fun getAllCountries(): CountriesResponse = withContext(dispatcher) {
        return@withContext httpClient.get("all_countries.php")
            .body()
    }

    override suspend fun getAllTeamsByCountry(country: String): TeamsResponse = withContext(dispatcher) {
        return@withContext httpClient.get("search_all_teams.php") {
            url {
                parameters.append("s", "Soccer")
                parameters.append("c", country)
            }
        }.body()
    }

    override suspend fun getLastEventByTeam(idTeam: String): LastEventsResponse = withContext(dispatcher) {
        return@withContext httpClient.get("eventslast.php") {
            url {
                parameters.append("id", idTeam)
            }
        }.body()
    }

}