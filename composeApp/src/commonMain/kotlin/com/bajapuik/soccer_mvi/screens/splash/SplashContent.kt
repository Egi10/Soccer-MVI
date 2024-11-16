package com.bajapuik.soccer_mvi.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import com.bajapuik.soccer_mvi.resources.Res
import io.github.alexzhirkevich.compottie.*
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashContent(
    onSplashUiAction: OnSplashUiAction,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes("files/anim_soccer.lottie")
        )
    }
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(key1 = progress) {
        if (progress == 1.0f) {
            onSplashUiAction(
                SplashUiAction.OnFinishLottie
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = SoccerTheme.colors.n0
            )
    ) {
        Image(
            modifier = Modifier
                .align(
                    alignment = Alignment.Center
                ),
            painter = rememberLottiePainter(
                composition = composition,
                progress = {
                    progress
                }
            ),
            contentDescription = null
        )
    }
}