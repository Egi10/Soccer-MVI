package com.bajapuik.soccer_mvi.domain.model

data class Event(
    val id: String,
    val league: String,
    val leagueBadge: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: String,
    val awayScore: String,
    val timestamp: String,
    val homeTeamBadge: String,
    val awayTeamBadge: String
)