package org.pax.messenger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class CreateTokens


@Serializable
@SerialName("password")
data class CreateTokenPairDto(
    val username: String,
    val password: String
): CreateTokens()

@Serializable
@SerialName("refresh")
data class RefreshTokenPairDto(
    val refreshToken: String
): CreateTokens()