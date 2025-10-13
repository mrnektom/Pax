package org.pax.messenger.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.pax.messenger.model.UserDto
import java.util.UUID
import kotlin.uuid.toKotlinUuid

object Users : UUIDTable("users") {
    val username = varchar("username", 255)
    val password = varchar("password", 255)
    val email = varchar("email", 255)
    val picture = varchar("picture", 255).default("no_avatar.png")
}

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    var username by Users.username
    var password by Users.password
    var email by Users.email
    var picture by Users.picture

    companion object : UUIDEntityClass<User>(Users)
}

fun User.toDto() = UserDto(
    id = id.value.toKotlinUuid(),
    username = username,
    picture = picture,
    email = email
)