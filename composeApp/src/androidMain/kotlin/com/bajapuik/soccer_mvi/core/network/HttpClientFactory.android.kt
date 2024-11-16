package com.bajapuik.soccer_mvi.core.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(
        engineFactory = OkHttp
    ) {
        engine {
            config {
                followRedirects(true)
            }
        }
    }
}