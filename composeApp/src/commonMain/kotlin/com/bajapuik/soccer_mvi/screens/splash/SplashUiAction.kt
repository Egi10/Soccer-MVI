package com.bajapuik.soccer_mvi.screens.splash

typealias OnSplashUiAction = (SplashUiAction) -> Unit

sealed class SplashUiAction {
    data object OnFinishLottie : SplashUiAction()
}