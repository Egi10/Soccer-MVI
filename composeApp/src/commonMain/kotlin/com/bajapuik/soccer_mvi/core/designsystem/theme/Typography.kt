package com.bajapuik.soccer_mvi.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class SoccerTypography(
    val heading1: TextStyle,
    val heading2: TextStyle,
    val heading3: TextStyle,
    val body1Bold: TextStyle,
    val body1: TextStyle,
    val body2Bold: TextStyle,
    val body2: TextStyle,
    val body3Bold: TextStyle,
    val body3: TextStyle,
    val small: TextStyle
)

val LocalSoccerTypography = staticCompositionLocalOf {
    SoccerTypography(
        heading1 = TextStyle.Default,
        heading2 = TextStyle.Default,
        heading3 = TextStyle.Default,
        body1Bold = TextStyle.Default,
        body1 = TextStyle.Default,
        body2Bold = TextStyle.Default,
        body2 = TextStyle.Default,
        body3Bold = TextStyle.Default,
        body3 = TextStyle.Default,
        small = TextStyle.Default
    )
}