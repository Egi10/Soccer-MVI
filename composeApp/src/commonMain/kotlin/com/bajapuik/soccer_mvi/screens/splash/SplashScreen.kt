package com.bajapuik.soccer_mvi.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SplashScreen(
    component: SplashComponent,
    modifier: Modifier = Modifier
) {
    SplashContent(
        onSplashUiAction = component::onAction,
        modifier = modifier
    )
}

