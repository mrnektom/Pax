package org.pax.messenger.modules

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthorizationModule() {
    authentication {
        bearer {

        }
    }
}