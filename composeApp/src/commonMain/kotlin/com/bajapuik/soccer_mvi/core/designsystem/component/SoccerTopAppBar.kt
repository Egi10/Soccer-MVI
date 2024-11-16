package com.bajapuik.soccer_mvi.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import com.bajapuik.soccer_mvi.resources.Res
import com.bajapuik.soccer_mvi.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoccerTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SoccerTheme.colors.n0
        ),
        title = {
            Text(
                text = title,
                style = SoccerTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                color = SoccerTheme.colors.n800
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                colors = IconButtonDefaults.iconButtonColors(),
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = title,
                        tint = SoccerTheme.colors.n700
                    )
                }
            )
        }
    )
}