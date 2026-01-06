package org.pax.messenger

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan(
    "org.pax.messenger.**",
)
class ApplicationModule