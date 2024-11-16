package com.bajapuik.soccer_mvi.base

import com.arkivanov.decompose.ComponentContext
import com.bajapuik.soccer_mvi.di.*
import com.bajapuik.soccer_mvi.screens.root.RootComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration

object CoreApplication {
    fun initialize(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(
            listOf(
                networkModule,
                databaseModule,
                dispatcherModule,
                dataSourceModule,
                repositoryModule,
                domainModule,
                componentModule
            )
        )
    }
}

object DependencyInjection: KoinComponent {
    fun rootComponent(componentContext: ComponentContext) = get<RootComponent> {
        parametersOf(componentContext)
    }
}