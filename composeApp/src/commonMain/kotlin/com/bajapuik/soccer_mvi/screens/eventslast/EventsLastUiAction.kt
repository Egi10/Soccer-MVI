package com.bajapuik.soccer_mvi.screens.eventslast

typealias OnEventLastUiAction = (EventsLastUiAction) -> Unit

sealed class EventsLastUiAction {
    data object OnBackClick : EventsLastUiAction()
}