package com.bajapuik.soccer_mvi.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bajapuik.soccer_mvi.core.designsystem.component.SoccerShimmer
import com.bajapuik.soccer_mvi.core.designsystem.theme.SoccerTheme
import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.model.Team
import com.bajapuik.soccer_mvi.resources.Res
import com.bajapuik.soccer_mvi.resources.no_image
import com.bajapuik.soccer_mvi.screens.home.action.HomeUiAction
import com.bajapuik.soccer_mvi.screens.home.action.OnHomeUiAction
import com.bajapuik.soccer_mvi.screens.home.state.AllTeamUiState
import com.bajapuik.soccer_mvi.screens.home.state.CountryUiState
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HomeContent(
    countryUiState: CountryUiState,
    allTeamUiState: AllTeamUiState,
    onHomeUiAction: OnHomeUiAction,
    selectedCountry: Country?,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        contentPadding = PaddingValues(
            all = 24.dp
        )
    ) {
        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            CountrySelection(
                countryUiState = countryUiState,
                onHomeUiAction = onHomeUiAction,
                selectedCountry = selectedCountry
            )
        }

        item(
            span = {
                GridItemSpan(2)
            }
        ) {
            when {
                countryUiState is CountryUiState.Loading -> {
                    SoccerShimmer(
                        modifier = Modifier
                            .size(
                                width = 60.dp,
                                height = 20.dp
                            )
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(
                                    size = 8.dp
                                )
                            ),
                        durationMillis = 1000
                    )
                }

                else -> {
                    Text(
                        text = "All Teams",
                        style = SoccerTheme.typography.heading2,
                        color = SoccerTheme.colors.n700,
                        modifier = Modifier
                            .padding(
                                vertical = SoccerTheme.spacing.medium
                            )
                    )
                }
            }
        }

        itemsAllTeam(
            allTeamUiState = allTeamUiState,
            onHomeUiAction = onHomeUiAction
        )
    }
}

private fun LazyGridScope.itemsAllTeam(
    allTeamUiState: AllTeamUiState,
    onHomeUiAction: OnHomeUiAction
) {
    when (allTeamUiState) {
        AllTeamUiState.Loading -> {
            items(10) {
                SoccerShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(
                                size = 8.dp
                            )
                        ),
                    durationMillis = 1000
                )
            }
        }

        is AllTeamUiState.Success -> {
            items(
                items = allTeamUiState.teams,
                key = { team ->
                    team.id
                }
            ) { team ->
                TeamItem(
                    team = team,
                    onHomeUiAction = onHomeUiAction
                )
            }
        }

        is AllTeamUiState.Error -> {
            item {
                Text(
                    text = allTeamUiState.message,
                    style = SoccerTheme.typography.body1Bold,
                    color = SoccerTheme.colors.n700
                )
            }
        }
    }
}

@Composable
private fun TeamItem(
    team: Team,
    onHomeUiAction: OnHomeUiAction,
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
        onClick = {
            onHomeUiAction(
                HomeUiAction.OnItemClick(
                    idEvent = team.id
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(
                        all = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = team.logo,
                    contentDescription = team.id,
                    placeholder = painterResource(
                        resource = Res.drawable.no_image
                    ),
                    error = painterResource(
                        resource = Res.drawable.no_image
                    ),
                    modifier = Modifier
                        .padding(
                            top = SoccerTheme.spacing.medium,
                            end = SoccerTheme.spacing.large,
                            start = SoccerTheme.spacing.large
                        )
                )

                Spacer(
                    modifier = Modifier
                        .height(SoccerTheme.spacing.medium)
                )

                Text(
                    text = team.name,
                    style = SoccerTheme.typography.body3,
                    color = SoccerTheme.colors.n600,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Composable
private fun CountrySelection(
    countryUiState: CountryUiState,
    onHomeUiAction: OnHomeUiAction,
    selectedCountry: Country?,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 48.dp
        ),
        contentPadding = PaddingValues(
            end = SoccerTheme.spacing.large
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        when (countryUiState) {
            CountryUiState.Loading -> {
                items(10) {
                    SoccerShimmer(
                        modifier = Modifier
                            .size(
                                width = 80.dp,
                                height = 20.dp
                            )
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(
                                    size = 8.dp
                                )
                            ),
                        durationMillis = 1000
                    )
                }
            }

            is CountryUiState.Success -> {
                items(
                    items = countryUiState.countries,
                    key = { country ->
                        country.name
                    }
                ) { country ->
                    ItemCountry(
                        country = country,
                        countrySelection = selectedCountry,
                        modifier = Modifier
                            .clickable {
                                onHomeUiAction(
                                    HomeUiAction.OnSelectedCountry(country)
                                )
                            }
                    )
                }
            }

            is CountryUiState.Error -> {
                item {
                    Text(
                        text = countryUiState.message,
                        style = SoccerTheme.typography.body1Bold,
                        color = SoccerTheme.colors.n700
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemCountry(
    country: Country,
    countrySelection: Country?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = country.name,
            style = if (country == countrySelection) {
                SoccerTheme.typography.body3Bold
            } else {
                SoccerTheme.typography.body3
            },
            color = if (country == countrySelection) {
                SoccerTheme.colors.n700
            } else {
                SoccerTheme.colors.n400
            }
        )

        if (country == countrySelection) {
            Spacer(
                modifier = Modifier
                    .height(SoccerTheme.spacing.small)
            )

            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(
                        color = SoccerTheme.colors.n700,
                        shape = CircleShape
                    )
            )
        }
    }
}