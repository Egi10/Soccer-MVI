package com.bajapuik.soccer_mvi.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme

@Composable
internal fun HomeScreen(
    component: HomeComponent,
    modifier: Modifier = Modifier
) {
    val countryUiState by component.countriesUiState.collectAsState()
    val allTeamUiState by component.allTeamState.collectAsState()

    LaunchedEffect(
        key1 = component.countrySelected
    ) {
        component.countrySelected?.let {
            component.getTeamsByCountry(
                country = it.name
            )
        }
    }

    HomeContent(
        countryUiState = countryUiState,
        allTeamUiState = allTeamUiState,
        onHomeUiAction = component::onAction,
        selectedCountry = component.countrySelected,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = SoccerTheme.colors.n0
            )
            .statusBarsPadding()
    )
}