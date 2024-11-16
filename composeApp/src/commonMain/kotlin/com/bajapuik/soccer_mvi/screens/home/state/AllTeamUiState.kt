package com.bajapuik.soccer_mvi.screens.home.state

import com.bajapuik.soccer_mvi.domain.model.Team

sealed interface AllTeamUiState {
    data object Loading : AllTeamUiState
    data class Success(val teams: List<Team>) : AllTeamUiState
    data class Error(val message: String) : AllTeamUiState
}