package org.pax.messenger.routing

import io.ktor.http.HttpHeaders
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.pingRouting() {
    get("/ping") {
        call.response.headers.append(HttpHeaders.Server, "pax-backend")
        call.respondText("pong")
    }
}