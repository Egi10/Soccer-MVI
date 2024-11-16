package com.bajapuik.soccer_mvi.screens.dashboard.navigation

import com.bajapuik.soccer_mvi.screens.eventslast.EventsLastComponent
import com.bajapuik.soccer_mvi.screens.favorite.FavoriteComponent
import com.bajapuik.soccer_mvi.screens.home.HomeComponent

sealed class DashboardChild {
    class HomeChild(val component: HomeComponent) : DashboardChild()
    class FavoriteChild(val component: FavoriteComponent) : DashboardChild()
    class DetailEventChild(val component: EventsLastComponent) : DashboardChild()
}