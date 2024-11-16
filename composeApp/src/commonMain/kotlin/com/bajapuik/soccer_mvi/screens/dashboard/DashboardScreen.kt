package com.bajapuik.soccer_mvi.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.bajapuik.soccer_mvi.core.designsystem.component.SoccerBottomNavigation
import com.bajapuik.soccer_mvi.core.designsystem.component.SoccerBottomNavigationItem
import com.bajapuik.soccer_mvi.resources.Res
import com.bajapuik.soccer_mvi.resources.ic_favorite
import com.bajapuik.soccer_mvi.resources.ic_home
import com.bajapuik.soccer_mvi.screens.dashboard.navigation.DashboardChild
import com.bajapuik.soccer_mvi.screens.eventslast.EventsLastScreen
import com.bajapuik.soccer_mvi.screens.favorite.FavoriteScreen
import com.bajapuik.soccer_mvi.screens.home.HomeScreen

@Composable
internal fun DashboardScreen(
    component: DashboardComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()
    val activeComponent = stack.active.instance

    Column(
        modifier = modifier
    ) {
        Children(
            stack = component.stack,
            modifier = Modifier
                .weight(1F)
                .consumeWindowInsets(WindowInsets.navigationBars),
            animation = stackAnimation { child ->
                when (child.instance) {
                    is DashboardChild.DetailEventChild -> fade().plus(slide())
                    else -> fade()
                }
            },
        ) {
            when (val child = it.instance) {
                is DashboardChild.HomeChild -> {
                    HomeScreen(
                        component = child.component
                    )
                }

                is DashboardChild.FavoriteChild -> {
                    FavoriteScreen(
                        component = child.component
                    )
                }

                is DashboardChild.DetailEventChild -> {
                    EventsLastScreen(
                        component = child.component
                    )
                }
            }
        }

        /**
         * The BottomBar is rendered for specific child components within the
         * Dashboard route. Since the logic for displaying the BottomBar is
         * not complex, it is sufficient to handle both HomeChild and
         * FavoriteChild within the same route. If additional complexity arises
         * or if more child components are added in the future, it may be
         * beneficial to create a separate route for better organization and
         * maintainability.
         */
        when (activeComponent) {
            is DashboardChild.HomeChild, is DashboardChild.FavoriteChild -> {
                SoccerBottomNavigation(
                    items = listOf(
                        SoccerBottomNavigationItem(
                            icon = Res.drawable.ic_home,
                            label = "Home"
                        ),
                        SoccerBottomNavigationItem(
                            icon = Res.drawable.ic_favorite,
                            label = "Favorite"
                        )
                    ),
                    onItemClick = {
                        when (it.label) {
                            "Home" -> {
                                component.onAction(
                                    uiAction = DashboardUiAction.OnHomeTabClick
                                )
                            }

                            "Favorite" -> {
                                component.onAction(
                                    uiAction = DashboardUiAction.OnFavoriteTabClick
                                )
                            }
                        }
                    }
                )
            }

            else -> {}
        }
    }
}