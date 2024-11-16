package com.bajapuik.soccer_mvi.screens.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import com.bajapuik.soccer_mvi.screens.dashboard.DashboardScreen
import com.bajapuik.soccer_mvi.screens.root.navigation.RootChild
import com.bajapuik.soccer_mvi.screens.splash.SplashScreen

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    SoccerTheme {
        Children(
            stack = component.stack,
            modifier = modifier,
            animation = stackAnimation(slide())
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when(val instance = it.instance) {
                    is RootChild.SplashScreen -> SplashScreen(
                        component = instance.component
                    )

                    is RootChild.DashboardScreen -> DashboardScreen(
                        component = instance.component
                    )
                }
            }
        }
    }
}