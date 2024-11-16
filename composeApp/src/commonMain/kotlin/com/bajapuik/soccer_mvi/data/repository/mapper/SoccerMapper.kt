package com.bajapuik.soccer_mvi.data.repository.mapper

import com.bajapuik.soccer_mvi.data.datasource.remote.response.CountryResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.EventResponse
import com.bajapuik.soccer_mvi.data.datasource.remote.response.TeamResponse
import com.bajapuik.soccer_mvi.domain.model.Country
import com.bajapuik.soccer_mvi.domain.model.Event
import com.bajapuik.soccer_mvi.domain.model.Team

internal fun List<CountryResponse>.asCountryDomainModel(): List<Country> {
    return this.map {
        Country(
            name = it.name
        )
    }
}

internal fun List<TeamResponse>.asTeamDomainModel(): List<Team> {
    return this.map {
        Team(
            id = it.id,
            name = it.name,
            logo = it.badge ?: it.logo.orEmpty()
        )
    }
}

internal fun List<EventResponse>.asEventDomainModel(): List<Event> {
    return this.map {
        Event(
            id = it.id,
            league = it.league,
            leagueBadge = it.leagueBadge,
            homeTeam = it.homeTeam,
            awayTeam = it.awayTeam,
            homeScore = it.homeScore,
            awayScore = it.awayScore,
            timestamp = it.timestamp,
            homeTeamBadge = it.homeTeamBadge.orEmpty(),
            awayTeamBadge = it.awayTeamBadge.orEmpty()
        )
    }
}