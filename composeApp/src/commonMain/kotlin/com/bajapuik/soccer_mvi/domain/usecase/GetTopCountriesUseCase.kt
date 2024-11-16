package com.bajapuik.soccer_mvi.domain.usecase

import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.repository.SoccerRepository
import com.bajapuik.soccer_mvi.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTopCountriesUseCase(
    private val soccerRepository: SoccerRepository
) {
    operator fun invoke(): Flow<Result<List<Country>>> {
        return soccerRepository.getAllCountries()
            .map { countries ->
                return@map when (countries) {
                    Result.Loading -> {
                        Result.Loading
                    }

                    is Result.Success -> {
                        val selectedCountryNames = setOf(
                            "Indonesia",
                            "Spain",
                            "England",
                            "France",
                            "Italy"
                        )

                        val newData = countries.data.filter { country ->
                            country.name in selectedCountryNames
                        }
                        Result.Success(newData)
                    }

                    is Result.Error<*> -> {
                        Result.Error(
                            message = countries.message,
                            data = countries.data
                        )
                    }
                }
            }
    }
}