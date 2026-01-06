package org.pax.messenger.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import org.koin.core.annotation.Single
import org.pax.messenger.components.defaultHttpClient

@Single
class ApiService {

    suspend fun checkApiHost(host: String): Boolean = runCatching {
        val client = defaultHttpClient()
        val server = client.get {
            url {
                takeFrom(host)
                pathSegments = listOf("ping")
            }
            println(url)
        }.headers[HttpHeaders.Server]

        return server == "pax-backend"
    }.getOrElse { false }
}

class AuthApi(
    val httpClient: HttpClient
) {
    suspend fun login(
        username: String,
        password: String,
    ) = httpClient.post("/v1/tokens") {
        contentType(ContentType.Application.Json)

        setBody(
            mapOf(
                "username" to username,
                "password" to password
            )
        )
    }
}
