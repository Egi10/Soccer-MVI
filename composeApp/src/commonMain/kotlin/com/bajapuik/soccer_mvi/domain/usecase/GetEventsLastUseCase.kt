package com.bajapuik.soccer_mvi.domain.usecase

import com.bajapuik.soccer_mvi.domain.model.Event
import com.bajapuik.soccer_mvi.domain.repository.SoccerRepository
import com.bajapuik.soccer_mvi.domain.utils.Result
import com.bajapuik.soccer_mvi.domain.utils.asResult
import kotlinx.coroutines.flow.Flow

class GetEventsLastUseCase(
    private val soccerRepository: SoccerRepository
) {
    operator fun invoke(idTeam: String): Flow<Result<List<Event>>> {
        return soccerRepository.getLastEventByTeam(
            idTeam = idTeam
        ).asResult()
    }
}