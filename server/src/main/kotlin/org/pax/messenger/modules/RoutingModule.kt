package org.pax.messenger.modules

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.pax.messenger.routing.chatsRouting
import org.pax.messenger.routing.emailVerificationCodesRouting
import org.pax.messenger.routing.pingRouting
import org.pax.messenger.routing.usersRouting

fun Application.routingModule() {
    routing {
        emailVerificationCodesRouting()
        chatsRouting()
        usersRouting()
        pingRouting()
    }
}