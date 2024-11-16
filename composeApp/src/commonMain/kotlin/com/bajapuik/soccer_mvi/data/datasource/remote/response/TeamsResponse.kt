package com.bajapuik.soccer_mvi.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamsResponse(
    @SerialName("teams")
    val teams: List<TeamResponse>
)

@Serializable
data class TeamResponse(
    @SerialName("idTeam")
    val id: String,
    @SerialName("strTeam")
    val name: String,
    @SerialName("strLogo")
    val logo: String?,
    @SerialName("strBadge")
    val badge: String?,
    @SerialName("strTeamAlternate")
    val alternateName: String?,
    @SerialName("intFormedYear")
    val formedYear: String?,
    @SerialName("idLeague")
    val leagueId: String?,
    @SerialName("strLeague")
    val league: String?,
    @SerialName("strStadium")
    val stadium: String?,
    @SerialName("intStadiumCapacity")
    val stadiumCapacity: String?,
    @SerialName("strLocation")
    val location: String?,
    @SerialName("strDescriptionEN")
    val description: String?,
    @SerialName("strCountry")
    val country: String?,
    @SerialName("strGender")
    val gender: String?
)