package org.pax.messenger.modules

import io.ktor.server.application.Application
import io.ktor.server.application.log
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pax.messenger.configuration.configureDatabase
import org.pax.messenger.service.EmailVerificationCodesService
import org.pax.messenger.service.TokensService
import org.pax.messenger.service.UsersService

val servicesModule = module {
    singleOf(::EmailVerificationCodesService)
    singleOf(::TokensService)
    singleOf(::UsersService)
}

val applicationModule = module {
    configureDatabase()
}

fun Application.dependencyInjectionModule() {
    log.debug(environment.config.keys().joinToString(prefix = "[", postfix = "]"))
    startKoin {
        val initModule = module {
            single {
                this@dependencyInjectionModule.environment
            }
        }

        modules(
            initModule,
            applicationModule,
            servicesModule
        )
    }
}