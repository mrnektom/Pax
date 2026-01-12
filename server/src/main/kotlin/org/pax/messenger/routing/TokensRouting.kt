package org.pax.messenger.routing

import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import org.pax.messenger.model.CreateTokenPairDto
import org.pax.messenger.service.TokensService

fun Routing.tokensRouting() {
    val tokensService by inject<TokensService>()

    route("/v1/tokens") {
        post<CreateTokenPairDto> { body ->
            call.respond(tokensService.createToken(body.username, body.password))
        }
    }
}