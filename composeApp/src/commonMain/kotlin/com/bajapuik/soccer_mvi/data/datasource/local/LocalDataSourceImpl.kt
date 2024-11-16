package com.bajapuik.soccer_mvi.data.datasource.local

import com.bajapuik.soccermvi.db.Country
import com.bajapuik.soccermvi.db.CountryQueries
import com.bajapuik.soccermvi.db.Team
import com.bajapuik.soccermvi.db.TeamQueries
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val teamQueries: TeamQueries,
    private val countryQueries: CountryQueries,
    private val dispatcher: CoroutineDispatcher
) : LocalDataSource {

    override suspend fun insertAllCountry(country: List<Country>) {
        countryQueries.transaction {
            country.forEach {
                val checkCountry = countryQueries.getCountryByName(
                    name = it.name
                ).executeAsOneOrNull()

                if (checkCountry == null) {
                    countryQueries.insertCountry(
                        name = it.name,
                        flagUrl = it.flagUrl
                    )
                } else {
                    countryQueries.updateCountry(
                        name = it.name,
                        flagUrl = it.flagUrl
                    )
                }
            }
        }
    }

    override suspend fun getCountry(): List<Country> = withContext(dispatcher) {
        return@withContext countryQueries.getAllCountries().executeAsList()
    }

    override suspend fun insertTeam(team: Team) = withContext(dispatcher) {
        teamQueries.transaction {
            val checkTeam = teamQueries.selectTeamById(
                id = team.id
            ).executeAsOneOrNull()

            if (checkTeam == null) {
                teamQueries.insertTeam(
                    id = team.id,
                    name = team.name,
                    alternateName = team.alternateName,
                    formedYear = team.formedYear,
                    league = team.league,
                    leagueId = team.leagueId,
                    stadium = team.stadium,
                    stadiumCapacity = team.stadiumCapacity,
                    location = team.location,
                    description = team.description,
                    badgeUrl = team.badgeUrl,
                    country = team.country,
                    gender = team.gender
                )
            } else {
                teamQueries.updateTeam(
                    id = team.id,
                    name = team.name,
                    alternateName = team.alternateName,
                    formedYear = team.formedYear,
                    league = team.league,
                    leagueId = team.leagueId,
                    stadium = team.stadium,
                    stadiumCapacity = team.stadiumCapacity,
                    location = team.location,
                    description = team.description,
                    badgeUrl = team.badgeUrl,
                    country = team.country,
                    gender = team.gender
                )
            }
        }
    }

    override suspend fun getTeamsByCountry(country: String): List<Team> = withContext(dispatcher) {
        return@withContext teamQueries.selectTeamByCountry(
            country = country
        ).executeAsList()
    }
}