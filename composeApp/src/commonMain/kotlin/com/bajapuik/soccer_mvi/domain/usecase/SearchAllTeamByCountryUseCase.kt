package com.bajapuik.soccer_mvi.domain.usecase

import co.touchlab.kermit.Logger
import com.bajapuik.soccer_mvi.domain.model.Team
import com.bajapuik.soccer_mvi.domain.repository.SoccerRepository
import com.bajapuik.soccer_mvi.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class SearchAllTeamByCountryUseCase(
    private val soccerRepository: SoccerRepository
) {
    operator fun invoke(country: String): Flow<Result<List<Team>>> {
        return soccerRepository.getAllTeamByCountry(
            country = country
        )
    }
}