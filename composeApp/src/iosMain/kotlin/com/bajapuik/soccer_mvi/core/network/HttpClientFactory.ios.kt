package com.bajapuik.soccer_mvi.core.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(
        engineFactory = Darwin
    ) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}