package org.pax.messenger.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class UserDto(
    val id: Uuid,
    val username: String,
    val picture: String,
    val email: String?,
)