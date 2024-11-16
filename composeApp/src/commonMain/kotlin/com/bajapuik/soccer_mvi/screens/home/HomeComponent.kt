package com.bajapuik.soccer_mvi.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.usecase.GetTopCountriesUseCase
import com.bajapuik.soccer_mvi.domain.usecase.SearchAllTeamByCountryUseCase
import com.bajapuik.soccer_mvi.domain.utils.Result
import com.bajapuik.soccer_mvi.screens.home.action.HomeUiAction
import com.bajapuik.soccer_mvi.screens.home.state.AllTeamUiState
import com.bajapuik.soccer_mvi.screens.home.state.CountryUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeComponent(
    getTopCountriesUseCase: GetTopCountriesUseCase,
    private val searchAllTeamByCountryUseCase: SearchAllTeamByCountryUseCase,
    componentContext: ComponentContext,
    dispatcher: CoroutineDispatcher,
    private val onItemClick: (String) -> Unit
) : ComponentContext by componentContext {
    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(
        context = dispatcher + SupervisorJob()
    )

    var countrySelected by mutableStateOf<Country?>(null)
        private set

    private val _allTeamState = MutableStateFlow<AllTeamUiState>(AllTeamUiState.Loading)
    val allTeamState get() = _allTeamState.asStateFlow()

    fun onAction(uiAction: HomeUiAction) {
        when (uiAction) {
            is HomeUiAction.OnSelectedCountry -> {
                countrySelected = uiAction.country
            }

            is HomeUiAction.OnItemClick -> {
                onItemClick.invoke(uiAction.idEvent)
            }
        }
    }

    val countriesUiState: StateFlow<CountryUiState> = getTopCountriesUseCase.invoke()
        .map {
            return@map when (it) {
                Result.Loading -> {
                    CountryUiState.Loading
                }

                is Result.Success -> {
                    if (it.data.isNotEmpty()) {
                        countrySelected = it.data.firstOrNull()
                    }

                    CountryUiState.Success(it.data)
                }

                is Result.Error<*> -> {
                    CountryUiState.Error(it.message)
                }
            }
        }.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CountryUiState.Loading
        )

    fun getTeamsByCountry(country: String) {
        scope.launch {
            searchAllTeamByCountryUseCase.invoke(
                country = country
            ).collectLatest {
                when (it) {
                    Result.Loading -> {
                        _allTeamState.update {
                            AllTeamUiState.Loading
                        }
                    }

                    is Result.Success -> {
                        val data = it.data
                        _allTeamState.update {
                            AllTeamUiState.Success(data)
                        }
                    }

                    is Result.Error<*> -> {
                        val message = it.message
                        _allTeamState.update {
                            AllTeamUiState.Error(message)
                        }
                    }
                }
            }
        }
    }
}