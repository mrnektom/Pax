package org.pax.messenger.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import org.pax.messenger.service.EmailVerificationCodesService

fun Routing.emailVerificationCodesRouting() {
    val emailVerificationCodesService by inject<EmailVerificationCodesService>()

    route("v1/email-verification-codes") {
        get {
            call.respond(HttpStatusCode.NotImplemented)
        }

        post {
            call.respond(HttpStatusCode.NotImplemented)
        }
    }
}