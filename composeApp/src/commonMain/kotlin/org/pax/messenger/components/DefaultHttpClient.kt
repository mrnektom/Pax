package org.pax.messenger.components

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun defaultHttpClient(block: HttpClientConfig<CIOEngineConfig>.() -> Unit = {}) = HttpClient(CIO) {
    expectSuccess = true

    engine {
        endpoint {
            connectAttempts = 5
        }
    }

    install(ContentNegotiation) {
        json()
    }

    block()
}