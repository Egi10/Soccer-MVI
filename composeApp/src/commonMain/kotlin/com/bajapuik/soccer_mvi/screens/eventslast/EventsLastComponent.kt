package com.bajapuik.soccer_mvi.screens.eventslast

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.bajapuik.soccer_mvi.domain.usecase.GetEventsLastUseCase
import com.bajapuik.soccer_mvi.screens.eventslast.state.EventsLastUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import com.bajapuik.soccer_mvi.domain.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class EventsLastComponent(
    getEventsLastUseCase: GetEventsLastUseCase,
    componentContext: ComponentContext,
    dispatcher: CoroutineDispatcher,
    idTeam: String,
    private val onBackClick: () -> Unit
) : ComponentContext by componentContext {
    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(
        context = dispatcher + SupervisorJob()
    )

    fun onAction(uiAction: EventsLastUiAction) {
        when(uiAction) {
            EventsLastUiAction.OnBackClick -> {
                onBackClick.invoke()
            }
        }
    }

    val eventsLastUiState: StateFlow<EventsLastUiState> = getEventsLastUseCase.invoke(
        idTeam = idTeam
    ).map {
        return@map when (it) {
                Result.Loading -> {
                    EventsLastUiState.Loading
                }

                is Result.Success -> {
                    val data = it.data

                    if (data.isEmpty()) {
                        EventsLastUiState.Empty
                    } else {
                        EventsLastUiState.Success(it.data)
                    }
                }

                is Result.Error<*> -> {
                    EventsLastUiState.Error(it.message)
                }
            }
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = EventsLastUiState.Loading
    )
}