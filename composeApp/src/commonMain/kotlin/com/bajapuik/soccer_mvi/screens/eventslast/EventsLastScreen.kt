package com.bajapuik.soccer_mvi.screens.eventslast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme

@Composable
internal fun EventsLastScreen(
    component: EventsLastComponent,
    modifier: Modifier = Modifier
) {
    val eventsLastState by component.eventsLastUiState.collectAsState()

    EventsLastContent(
        eventsLastUiState = eventsLastState,
        onEventLastUiAction = component::onAction,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = SoccerTheme.colors.n0
            )
            .systemBarsPadding()
    )
}