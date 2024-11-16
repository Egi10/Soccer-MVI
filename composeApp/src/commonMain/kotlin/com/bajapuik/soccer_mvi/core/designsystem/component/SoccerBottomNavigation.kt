package com.bajapuik.soccer_mvi.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class SoccerBottomNavigationItem(
    val icon: DrawableResource,
    val label: String
)

@Composable
fun SoccerBottomNavigation(
    items: List<SoccerBottomNavigationItem>,
    onItemClick: (SoccerBottomNavigationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    // State untuk menyimpan indeks item yang terpilih
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets.navigationBars,
        containerColor = SoccerTheme.colors.n0,
        tonalElevation = 2.dp,
        content = {
            items.forEachIndexed { index, item ->
                SoccerBottomNavigationItem(
                    isSelect = selectedItem == index,
                    icon = item.icon,
                    label = item.label,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {
                                selectedItem = index
                                onItemClick(item)
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                color = SoccerTheme.colors.b200
                            )
                        )
                )
            }
        }
    )
}

@Composable
private fun SoccerBottomNavigationItem(
    isSelect: Boolean,
    icon: DrawableResource,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .semantics {
                contentDescription = "Navigate $label"
                role = Role.Tab
                selected = isSelect
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(SoccerTheme.spacing.medium)
        )

        Icon(
            painter = painterResource(icon),
            tint = if (isSelect) {
                SoccerTheme.colors.b400
            } else {
                SoccerTheme.colors.n400
            },
            contentDescription = label,
            modifier = Modifier
                .size(
                    size = 24.dp
                )
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Text(
            text = label,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = SoccerTheme.typography.body3,
            color = if (isSelect) {
                SoccerTheme.colors.b400
            } else {
                SoccerTheme.colors.n400
            },
        )

        Spacer(
            modifier = Modifier
                .height(SoccerTheme.spacing.small)
        )
    }
}