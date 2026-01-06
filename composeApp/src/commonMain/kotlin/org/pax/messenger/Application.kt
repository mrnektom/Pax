package org.pax.messenger

import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.org_pax_messenger_ApplicationModule


@KoinApplication(
    modules = [ApplicationModule::class]
)
object Application

