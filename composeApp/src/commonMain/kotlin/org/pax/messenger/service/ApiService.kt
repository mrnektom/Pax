package org.pax.messenger.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
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
import org.pax.messenger.TokenPairDto
import org.pax.messenger.components.defaultHttpClient

@Single
class ApiService {
    private val authApis = mutableMapOf<String, AuthApi>()

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


    fun getAuthApi(host: String): AuthApi {
        return authApis.getOrElse(host) {
            AuthApi(defaultHttpClient {
                defaultRequest {
                    url(host)
                }
            })
        }
    }
}

class AuthApi(
    val httpClient: HttpClient
) {
    suspend fun login(username: String, password: String) = httpClient
        .post("/v1/tokens") {
            contentType(ContentType.Application.Json)

            setBody(
                mapOf(
                    "username" to username,
                    "password" to password
                )
            )
        }
        .body<TokenPairDto>()

    suspend fun createUser(
        email: String,
        username: String,
        password: String,
    ) = httpClient
        .post("/v1/users") {
            contentType(ContentType.Application.Json)

            setBody(
                mapOf(
                    "email" to email,
                    "username" to username,
                    "password" to password
                )
            )
        }
}
