package org.pax.messenger.model

import kotlin.uuid.Uuid

data class UserDto(
    val id: Uuid,
    val username: String,
    val picture: String,
    val email: String?,
)