package com.bajapuik.soccer_mvi.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastEventsResponse(
    @SerialName("results")
    val results: List<EventResponse>?
)

@Serializable
data class EventResponse(
    @SerialName("idEvent")
    val id: String,
    @SerialName("strLeague")
    val league: String,
    @SerialName("strLeagueBadge")
    val leagueBadge: String,
    @SerialName("strHomeTeam")
    val homeTeam: String,
    @SerialName("strAwayTeam")
    val awayTeam: String,
    @SerialName("intHomeScore")
    val homeScore: String,
    @SerialName("intAwayScore")
    val awayScore: String,
    @SerialName("strTimestamp")
    val timestamp: String,
    @SerialName("strHomeTeamBadge")
    val homeTeamBadge: String?,
    @SerialName("strAwayTeamBadge")
    val awayTeamBadge: String?
)