package com.bajapuik.soccer_mvi.screens.dashboard

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.bajapuik.soccer_mvi.screens.dashboard.navigation.DashboardChild
import com.bajapuik.soccer_mvi.screens.dashboard.navigation.DashboardRouteConfig
import com.bajapuik.soccer_mvi.screens.eventslast.EventsLastComponent
import com.bajapuik.soccer_mvi.screens.favorite.FavoriteComponent
import com.bajapuik.soccer_mvi.screens.home.HomeComponent

class DashboardComponent(
    private val homeComponentFactory: (ComponentContext, (String) -> Unit) -> HomeComponent,
    private val favoriteComponentFactory: (ComponentContext) -> FavoriteComponent,
    private val eventsLastComponentFactory: (ComponentContext, String, () -> Unit) -> EventsLastComponent,
    componentContext: ComponentContext
): ComponentContext by componentContext {
    private val nav = StackNavigation<DashboardRouteConfig>()

    private val _stack = childStack(
        source = nav,
        serializer	 = DashboardRouteConfig.serializer(),
        initialConfiguration = DashboardRouteConfig.Home,
        childFactory = ::child,
        handleBackButton = true
    )
    val stack: Value<ChildStack<*, DashboardChild>>
        get() = _stack

    fun onAction(uiAction: DashboardUiAction) {
        when(uiAction) {
            DashboardUiAction.OnHomeTabClick -> {
                nav.bringToFront(DashboardRouteConfig.Home)
            }

            DashboardUiAction.OnFavoriteTabClick -> {
                nav.bringToFront(DashboardRouteConfig.Favorite)
            }

            is DashboardUiAction.OnItemDetailClick -> {

            }
        }
    }

    private fun child(
        config: DashboardRouteConfig,
        childComponentContext: ComponentContext
    ): DashboardChild {
        return when(config) {
            DashboardRouteConfig.Home -> {
                DashboardChild.HomeChild(
                    component = homeComponentFactory(
                        childComponentContext
                    ) {
                        nav.pushNew(DashboardRouteConfig.DetailEvent(idEvent = it))
                    }
                )
            }

            DashboardRouteConfig.Favorite -> {
                DashboardChild.FavoriteChild(
                    component = favoriteComponentFactory(
                        childComponentContext
                    )
                )
            }

            is DashboardRouteConfig.DetailEvent -> {
                DashboardChild.DetailEventChild(
                    component = eventsLastComponentFactory(
                        childComponentContext,
                        config.idEvent,
                    ) {
                        nav.pop()
                    }
                )
            }
        }
    }
}