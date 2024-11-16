package com.bajapuik.soccer_mvi.screens.eventslast.state

import com.bajapuik.soccer_mvi.domain.model.Event

sealed interface EventsLastUiState {
    data object Loading : EventsLastUiState
    data object Empty : EventsLastUiState
    data class Success(val events: List<Event>) : EventsLastUiState
    data class Error(val message: String) : EventsLastUiState
}