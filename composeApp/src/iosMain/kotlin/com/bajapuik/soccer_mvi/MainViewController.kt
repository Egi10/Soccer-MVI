package com.bajapuik.soccer_mvi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.bajapuik.soccer_mvi.screens.root.RootComponent
import com.bajapuik.soccer_mvi.screens.root.RootScreen

fun MainViewController(root: RootComponent) = ComposeUIViewController {
    RootScreen(
        component = root,
        modifier = Modifier
            .fillMaxSize()
    )
}