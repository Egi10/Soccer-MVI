package com.bajapuik.soccer_mvi.screens.root.navigation

import com.bajapuik.soccer_mvi.screens.dashboard.DashboardComponent
import com.bajapuik.soccer_mvi.screens.splash.SplashComponent

sealed class RootChild {
    class SplashScreen(val component: SplashComponent) : RootChild()
    class DashboardScreen(val component: DashboardComponent) : RootChild()
}
