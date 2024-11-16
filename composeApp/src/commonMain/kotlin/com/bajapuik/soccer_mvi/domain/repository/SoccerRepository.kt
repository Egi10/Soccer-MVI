package com.bajapuik.soccer_mvi.domain.repository

import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.model.Event
import com.bajapuik.soccer_mvi.domain.model.Team
import com.bajapuik.soccer_mvi.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface SoccerRepository {
    fun getAllCountries(): Flow<Result<List<Country>>>
    fun getAllTeamByCountry(country: String): Flow<Result<List<Team>>>
    fun getLastEventByTeam(idTeam: String): Flow<List<Event>>
}