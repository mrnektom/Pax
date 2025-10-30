package org.pax.messenger.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import org.pax.messenger.helpers.jsonb
import java.util.UUID

object Tokens : UUIDTable("tokens") {
    val accessToken = varchar("access_token", 255)
    val refreshToken = varchar("refresh_token", 255)
    val expiresIn = timestamp("expires_in")
    val metadata = jsonb<Map<String, String>>("metadata")
}

class Token(id: EntityID<UUID>) : UUIDEntity(id) {
    var accessToken by Tokens.accessToken
    var refreshToken by Tokens.refreshToken
    var expiresIn by Tokens.expiresIn
    var metadata by Tokens.metadata

    companion object : UUIDEntityClass<Token>(Tokens)
}