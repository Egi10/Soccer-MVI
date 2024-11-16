package com.bajapuik.soccer_mvi.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bajapuik.soccer_mvi.resources.Res
import com.bajapuik.soccer_mvi.resources.poppins_bold
import com.bajapuik.soccer_mvi.resources.poppins_regular
import com.bajapuik.soccer_mvi.resources.poppins_semiBold
import org.jetbrains.compose.resources.Font

@Composable
fun SoccerTheme(
    content: @Composable () -> Unit
) {
    val soccerColors = SoccerColors(
        n800 = Color(0xFF303135),
        n700 = Color(0xFF4D4F56),
        n600 = Color(0xFF71747D),
        n400 = Color(0xFFAEB2BE),
        n200 = Color(0xFFD8DCE8),
        n100 = Color(0xFFF4F7FE),
        n0 = Color(0xFFFFFFFF),
        b400 = Color(0xFF007CFF),
        b300 = Color(0xFF5BAAFF),
        b200 = Color(0xFFA3CFFF),
        b100 = Color(0xFFE7F2FF),
        g500 = Color(0xFF0BAE54),
        g100 = Color(0xFFE2FBED),
        y100 = Color(0xFFFEF8D0),
        r400 = Color(0xFFF15C59),
        r100 = Color(0xFFFFDFDF)
    )

    val soccerTypography = SoccerTypography(
        heading1 = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        heading2 = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        heading3 = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        body1Bold = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        body1 = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_regular))
        ),
        body2Bold = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        body2 = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_regular))
        ),
        body3Bold = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_bold))
        ),
        body3 = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_regular))
        ),
        small = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            fontFamily = FontFamily(Font(Res.font.poppins_semiBold))
        )
    )

    val soccerSpacing = SoccerSpacing(
        small = 8.dp,
        medium = 12.dp,
        large = 16.dp
    )

    CompositionLocalProvider(
        LocalSoccerColors provides soccerColors,
        LocalSoccerTypography provides soccerTypography,
        LocalSoccerSpacing provides soccerSpacing,
        content = content
    )
}

object SoccerTheme {
    val colors: SoccerColors
        @Composable
        get() = LocalSoccerColors.current
    val typography: SoccerTypography
        @Composable
        get() = LocalSoccerTypography.current
    val spacing: SoccerSpacing
        @Composable
        get() = LocalSoccerSpacing.current
}