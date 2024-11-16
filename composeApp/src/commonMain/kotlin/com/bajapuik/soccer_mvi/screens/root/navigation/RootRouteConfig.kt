package com.bajapuik.soccer_mvi.screens.root.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootRouteConfig {
    @Serializable
    data object SplashScreen : RootRouteConfig
    @Serializable
    data object DashboardScreen : RootRouteConfig
}