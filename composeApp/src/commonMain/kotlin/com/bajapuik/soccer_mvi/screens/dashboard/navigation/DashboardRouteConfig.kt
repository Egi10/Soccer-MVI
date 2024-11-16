package com.bajapuik.soccer_mvi.screens.dashboard.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardRouteConfig {
    @Serializable
    data object Home : DashboardRouteConfig
    @Serializable
    data object Favorite : DashboardRouteConfig
    @Serializable
    data class DetailEvent(val idEvent: String) : DashboardRouteConfig
}