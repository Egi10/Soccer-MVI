package com.bajapuik.soccer_mvi.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class SoccerSpacing(
    val small: Dp,
    val medium: Dp,
    val large: Dp
)

val LocalSoccerSpacing = staticCompositionLocalOf {
    SoccerSpacing(
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified
    )
}