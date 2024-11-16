package com.bajapuik.soccer_mvi.screens.eventslast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bajapuik.soccer_mvi.core.common.DateTimeFormatter
import com.bajapuik.soccer_mvi.core.common.DateTimePattern
import com.bajapuik.soccer_mvi.core.designsystem.component.SoccerShimmer
import com.bajapuik.soccer_mvi.core.designsystem.component.SoccerTopAppBar
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import com.bajapuik.soccer_mvi.domain.model.Event
import com.bajapuik.soccer_mvi.resources.Res
import com.bajapuik.soccer_mvi.resources.no_image
import com.bajapuik.soccer_mvi.screens.eventslast.state.EventsLastUiState
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun EventsLastContent(
    eventsLastUiState: EventsLastUiState,
    onEventLastUiAction: OnEventLastUiAction,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = SoccerTheme.colors.n0,
        topBar = {
            SoccerTopAppBar(
                title = "Matching Results",
                onBackClick = {
                    onEventLastUiAction(
                        EventsLastUiAction.OnBackClick
                    )
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        paddingValues = innerPadding
                    )
                    .padding(
                        bottom = WindowInsets.navigationBars
                            .asPaddingValues()
                            .calculateBottomPadding()
                    )
            ) {
                when (eventsLastUiState) {
                    EventsLastUiState.Empty -> {
                        Column {
                            Text("Empty")
                        }
                    }

                    is EventsLastUiState.Error -> {
                        Column {
                            Text("Error")
                        }
                    }

                    EventsLastUiState.Loading -> {
                        ShimmerLoading(
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    is EventsLastUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement
                                .spacedBy(
                                    space = SoccerTheme.spacing.large
                                ),
                            contentPadding = PaddingValues(
                                all = SoccerTheme.spacing.large
                            )
                        ) {
                            items(
                                items = eventsLastUiState.events,
                                key = { event -> event.id }
                            ) { event ->
                                ItemEvent(event = event)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun ShimmerLoading(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(
                space = SoccerTheme.spacing.large
            ),
        contentPadding = PaddingValues(
            all = SoccerTheme.spacing.large
        )
    ) {
        items(10) {
            SoccerShimmer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                durationMillis = 1000
            )
        }
    }
}

@Composable
private fun ItemEvent(
    event: Event,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            size = 16.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = SoccerTheme.colors.n0
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        all = 16.dp
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                League(
                    event = event,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(SoccerTheme.spacing.medium)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TeamBadge(
                        badge = event.homeTeamBadge,
                        name = event.homeTeam,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Score(
                            event = event,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(
                            modifier = Modifier
                                .height(SoccerTheme.spacing.small)
                        )

                        val formatDate = DateTimeFormatter.formatDateTime(
                            timestamp = event.timestamp,
                            inputFormat = DateTimePattern.isoDateTime(
                                pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS]"
                            ),
                            outputFormat = DateTimePattern.shortDayMonthYear()
                        )

                        Text(
                            text = formatDate,
                            style = SoccerTheme.typography.body3,
                            color = SoccerTheme.colors.n600,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    TeamBadge(
                        badge = event.awayTeamBadge,
                        name = event.awayTeam,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    )
}

@Composable
private fun League(
    event: Event,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = event.leagueBadge,
            contentDescription = event.league,
            placeholder = painterResource(
                resource = Res.drawable.no_image
            ),
            error = painterResource(
                resource = Res.drawable.no_image
            ),
            modifier = Modifier
                .size(24.dp)
        )

        Spacer(
            modifier = Modifier
                .width(SoccerTheme.spacing.small)
        )

        Text(
            text = event.league,
            style = SoccerTheme.typography.body3,
            color = SoccerTheme.colors.n800,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Score(
    event: Event,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = event.homeScore,
            style = SoccerTheme.typography.heading1,
            color = SoccerTheme.colors.n800
        )

        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Text(
            text = ":",
            style = SoccerTheme.typography.heading1,
            color = SoccerTheme.colors.n800
        )

        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Text(
            text = event.awayScore,
            style = SoccerTheme.typography.heading1,
            color = SoccerTheme.colors.n800
        )
    }
}

@Composable
private fun TeamBadge(
    badge: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = badge,
            contentDescription = name,
            placeholder = painterResource(
                resource = Res.drawable.no_image
            ),
            error = painterResource(
                resource = Res.drawable.no_image
            ),
            modifier = Modifier
                .size(80.dp)
        )

        Spacer(
            modifier = Modifier
                .height(SoccerTheme.spacing.small)
        )

        Text(
            text = name,
            style = SoccerTheme.typography.body3Bold,
            color = SoccerTheme.colors.n800,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}