package org.pax.messenger.modules

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.pax.messenger.routing.usersRouting

fun Application.routingModule() {
    routing {
        usersRouting()
    }
}