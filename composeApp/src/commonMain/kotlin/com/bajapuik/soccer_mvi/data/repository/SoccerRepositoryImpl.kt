package com.bajapuik.soccer_mvi.data.repository

import com.bajapuik.soccer_mvi.data.datasource.local.LocalDataSource
import com.bajapuik.soccer_mvi.data.datasource.remote.RemoteDataSource
import com.bajapuik.soccer_mvi.data.repository.mapper.asEventDomainModel
import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.model.Event
import com.bajapuik.soccer_mvi.domain.model.Team
import com.bajapuik.soccer_mvi.domain.repository.SoccerRepository
import com.bajapuik.soccer_mvi.domain.utils.NetworkBoundResource
import com.bajapuik.soccer_mvi.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoccerRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkBoundResource: NetworkBoundResource
) : SoccerRepository {
    override fun getAllCountries(): Flow<Result<List<Country>>> {
        return networkBoundResource.execute(
            localFetch = {
                val result = localDataSource.getCountry()
                return@execute if (result.isNotEmpty()) {
                    result.map {
                        Country(
                            name = it.name
                        )
                    }
                } else {
                    null
                }
            },
            remoteFetch = {
                remoteDataSource.getAllCountries()
                    .countries
            },
            saveFetchResult = {
                localDataSource.insertAllCountry(
                    country = it.map { country ->
                        com.bajapuik.soccermvi.db.Country(
                            name = country.name,
                            flagUrl = country.flag
                        )
                    }
                )
            }
        )
    }

    override fun getAllTeamByCountry(country: String): Flow<Result<List<Team>>> {
        return networkBoundResource.execute(
            localFetch = {
                val result = localDataSource.getTeamsByCountry(country = country)
                return@execute if (result.isNotEmpty()) {
                    result.map {
                        Team(
                            id = it.id,
                            name = it.name,
                            logo = it.badgeUrl.orEmpty()
                        )
                    }
                } else {
                    null
                }
            },
            remoteFetch = {
                remoteDataSource.getAllTeamsByCountry(
                    country = country
                ).teams
            },
            saveFetchResult = {
                it.forEach { team ->
                    localDataSource.insertTeam(
                        team = com.bajapuik.soccermvi.db.Team(
                            id = team.id,
                            name = team.name,
                            alternateName = team.alternateName,
                            formedYear = team.formedYear?.toLong() ?: 0L,
                            league = team.league.orEmpty(),
                            leagueId = team.leagueId.orEmpty(),
                            stadium = team.stadium.orEmpty(),
                            stadiumCapacity = team.stadiumCapacity?.toLong() ?: 0L,
                            location = team.location.orEmpty(),
                            description = team.description.orEmpty(),
                            badgeUrl = team.badge ?: team.logo.orEmpty(),
                            country = team.country.orEmpty(),
                            gender = team.gender.orEmpty()
                        )
                    )
                }
            }
        )
    }

    override fun getLastEventByTeam(idTeam: String): Flow<List<Event>> {
        return flow {
            val result = remoteDataSource.getLastEventByTeam(
                idTeam = idTeam
            ).results?.asEventDomainModel()

            emit(result ?: listOf())
        }
    }

}