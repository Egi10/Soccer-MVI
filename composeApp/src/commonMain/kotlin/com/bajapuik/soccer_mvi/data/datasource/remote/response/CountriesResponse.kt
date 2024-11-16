package com.bajapuik.soccer_mvi.data.datasource.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountriesResponse(
    @SerialName("countries")
    val countries: List<CountryResponse>
)

@Serializable
data class CountryResponse(
    @SerialName("name_en")
    val name: String,
    @SerialName("flag_url_32")
    val flag: String
)