package com.bajapuik.soccer_mvi.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import sp.bvantur.inspektify.ktor.InspektifyKtor

expect fun createPlatformHttpClient(): HttpClient

object HttpClientFactory {
    fun create(
        json: Json,
        baseUrl: String,
        apiVersion: String,
        isDebugMode: Boolean = true
    ): HttpClient {
        return createPlatformHttpClient().config {
            install(ContentNegotiation) {
                json(
                    json = json
                )
            }

            defaultRequest {
                host = baseUrl

                url {
                    this.user
                    protocol = URLProtocol.HTTPS

                    appendPathSegments(apiVersion)
                }
            }

            install(HttpTimeout) {
                this.requestTimeoutMillis = 60_000
                this.connectTimeoutMillis = 60_000
                this.socketTimeoutMillis = 60_000
            }

            // Response Validation
            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->

                }
            }

            if (isDebugMode) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            co.touchlab.kermit.Logger.d("TAG") {
                                message
                            }
                        }
                    }
                    level = LogLevel.ALL
                }

                // Inspect Network
                install(InspektifyKtor) {
                    logLevel = sp.bvantur.inspektify.ktor.LogLevel.All
                }
            }
        }
    }
}