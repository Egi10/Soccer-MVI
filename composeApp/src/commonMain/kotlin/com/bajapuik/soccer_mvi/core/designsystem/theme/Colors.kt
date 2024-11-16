package com.bajapuik.soccer_mvi.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SoccerColors(
    val n800: Color,
    val n700: Color,
    val n600: Color,
    val n400: Color,
    val n200: Color,
    val n100: Color,
    val n0: Color,
    val b400: Color,
    val b300: Color,
    val b200: Color,
    val b100: Color,
    val g500: Color,
    val g100: Color,
    val y100: Color,
    val r400: Color,
    val r100: Color
)

val LocalSoccerColors = staticCompositionLocalOf {
    SoccerColors(
        n800 = Color.Unspecified,
        n700 = Color.Unspecified,
        n600 = Color.Unspecified,
        n400 = Color.Unspecified,
        n200 = Color.Unspecified,
        n100 = Color.Unspecified,
        n0 = Color.Unspecified,
        b400 = Color.Unspecified,
        b300 = Color.Unspecified,
        b200 = Color.Unspecified,
        b100 = Color.Unspecified,
        g500 = Color.Unspecified,
        g100 = Color.Unspecified,
        y100 = Color.Unspecified,
        r400 = Color.Unspecified,
        r100 = Color.Unspecified
    )
}