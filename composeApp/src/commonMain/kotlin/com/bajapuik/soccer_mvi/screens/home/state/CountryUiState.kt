package com.bajapuik.soccer_mvi.screens.home.state

import com.bajapuik.soccer_mvi.domain.model.Country

sealed interface CountryUiState {
    data object Loading : CountryUiState
    data class Success(val countries: List<Country>) : CountryUiState
    data class Error(val message: String) : CountryUiState
}