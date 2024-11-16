package com.bajapuik.soccer_mvi.core.network

import kotlinx.serialization.json.Json

object JsonFactory {
    fun create(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            useAlternativeNames = false
            explicitNulls = false
        }
    }
}