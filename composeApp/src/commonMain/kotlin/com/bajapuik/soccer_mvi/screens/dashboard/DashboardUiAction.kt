package com.bajapuik.soccer_mvi.screens.dashboard

typealias OnDashboardUiAction = (DashboardUiAction) -> Unit

sealed class DashboardUiAction {
    data object OnHomeTabClick : DashboardUiAction()
    data object OnFavoriteTabClick : DashboardUiAction()
    data class OnItemDetailClick(val idEvent: String) : DashboardUiAction()
}