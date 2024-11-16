package com.bajapuik.soccer_mvi.screens.splash

import com.arkivanov.decompose.ComponentContext

class SplashComponent(
    componentContext: ComponentContext,
    private val onFinishedLottie: () -> Unit
): ComponentContext by componentContext {

    fun onAction(uiAction: SplashUiAction) {
        when(uiAction) {
            SplashUiAction.OnFinishLottie -> {
                onFinishedLottie.invoke()
            }
        }
    }
}