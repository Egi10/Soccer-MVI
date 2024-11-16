package com.bajapuik.soccer_mvi.screens.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.bajapuik.soccer_mvi.screens.dashboard.DashboardComponent
import com.bajapuik.soccer_mvi.screens.root.navigation.RootChild
import com.bajapuik.soccer_mvi.screens.root.navigation.RootRouteConfig
import com.bajapuik.soccer_mvi.screens.splash.SplashComponent

class RootComponent(
    private val splashComponentFactory: (ComponentContext, () -> Unit) -> SplashComponent,
    private val dashboardComponentFactory: (ComponentContext) -> DashboardComponent,
    componentContext: ComponentContext
): ComponentContext by componentContext {
    private val navigation = StackNavigation<RootRouteConfig>()

    private val _stack = childStack(
        source = navigation,
        serializer = RootRouteConfig.serializer(),
        initialConfiguration = RootRouteConfig.SplashScreen,
        childFactory = ::child
    )
    val stack: Value<ChildStack<*, RootChild>>
        get() = _stack

    private fun child(config: RootRouteConfig, childComponentContext: ComponentContext): RootChild {
        return when(config) {
            is RootRouteConfig.SplashScreen -> RootChild.SplashScreen(
                component = splashComponentFactory(childComponentContext) {
                    navigation.replaceAll(RootRouteConfig.DashboardScreen)
                }
            )

            is RootRouteConfig.DashboardScreen -> RootChild.DashboardScreen(
                component = dashboardComponentFactory(childComponentContext)
            )
        }
    }
}