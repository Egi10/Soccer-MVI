package com.bajapuik.soccer_mvi.data.datasource.local

import com.bajapuik.soccermvi.db.Country
import com.bajapuik.soccermvi.db.Team

interface LocalDataSource {
    suspend fun insertAllCountry(country: List<Country>)
    suspend fun getCountry(): List<Country>
    suspend fun insertTeam(team: Team)
    suspend fun getTeamsByCountry(country: String): List<Team>
}