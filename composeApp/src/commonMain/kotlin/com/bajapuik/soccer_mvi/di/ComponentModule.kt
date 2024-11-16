package com.bajapuik.soccer_mvi.di

import com.arkivanov.decompose.ComponentContext
import com.bajapuik.soccer_mvi.screens.dashboard.DashboardComponent
import com.bajapuik.soccer_mvi.screens.eventslast.EventsLastComponent
import com.bajapuik.soccer_mvi.screens.favorite.FavoriteComponent
import com.bajapuik.soccer_mvi.screens.home.HomeComponent
import com.bajapuik.soccer_mvi.screens.root.RootComponent
import com.bajapuik.soccer_mvi.screens.splash.SplashComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val componentModule = module {
    factory { (componentContext: ComponentContext) ->
        RootComponent(
            splashComponentFactory = { component, function ->
                get {
                    parametersOf(component, function)
                }
            },
            dashboardComponentFactory = { component ->
                get {
                    parametersOf(component)
                }
            },
            componentContext = componentContext
        )
    }

    factory { (componentContext: ComponentContext) ->
        DashboardComponent(
            homeComponentFactory = { component, function ->
                get {
                    parametersOf(component, function)
                }
            },
            favoriteComponentFactory = { component ->
                get {
                    parametersOf(component)
                }
            },
            eventsLastComponentFactory = { component, idEvent, function ->
                get {
                    parametersOf(component, idEvent, function)
                }
            },
            componentContext = componentContext
        )
    }

    factory { (componentContext: ComponentContext, onFinishedLottie: () -> Unit) ->
        SplashComponent(
            componentContext = componentContext,
            onFinishedLottie = onFinishedLottie
        )
    }

    factory { (componentContext: ComponentContext, onItemClick: (String) -> Unit) ->
        HomeComponent(
            getTopCountriesUseCase = get(),
            searchAllTeamByCountryUseCase = get(),
            componentContext = componentContext,
            dispatcher = get(named(DISPATCHER_MAIN)),
            onItemClick = onItemClick
        )
    }

    factory { (componentContext: ComponentContext) ->
        FavoriteComponent(
            componentContext = componentContext
        )
    }

    factory { (componentContext: ComponentContext, idEvent: String, onItemClick: () -> Unit) ->
        EventsLastComponent(
            getEventsLastUseCase = get(),
            componentContext = componentContext,
            dispatcher = get(named(DISPATCHER_MAIN)),
            idTeam = idEvent,
            onBackClick = onItemClick
        )
    }
}