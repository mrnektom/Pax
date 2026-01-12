package org.pax.messenger

import kotlin.time.Instant

data class TokenPairDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Instant,
)
