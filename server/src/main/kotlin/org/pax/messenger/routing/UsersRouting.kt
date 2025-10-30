package org.pax.messenger.routing

import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.pax.messenger.service.UsersService


fun Routing.usersRouting() {
    val usersService by inject<UsersService>()

    route("/v1/users") {
        post<UsersService.CreateRequest> { request ->
            val user = usersService.createUser(request)
            call.respond(user)
        }
    }
}