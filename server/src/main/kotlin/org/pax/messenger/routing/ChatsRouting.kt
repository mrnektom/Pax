package org.pax.messenger.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.chatsRouting() {
    route("v1/chats") {
        get("/{id}") {
            call.respond(HttpStatusCode.NotImplemented)
        }

        get {
            call.respond(HttpStatusCode.NotImplemented)
        }

        post {
            call.respond(HttpStatusCode.NotImplemented)
        }

        patch {
            call.respond(HttpStatusCode.NotImplemented)
        }
    }
}