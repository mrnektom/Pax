package org.pax.messenger

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.pax.messenger.modules.configureAuthorizationModule
import org.pax.messenger.modules.dependencyInjectionModule
import org.pax.messenger.modules.routingModule
import org.pax.messenger.modules.serializationModule

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureAuthorizationModule()
    dependencyInjectionModule()
    serializationModule()
    routingModule()
}