package org.pax.messenger.service

import at.favre.lib.crypto.bcrypt.BCrypt
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.pax.messenger.model.UserDto
import org.pax.messenger.tables.User
import org.pax.messenger.tables.toDto

class UsersService() {
    suspend fun createUser(request: CreateRequest): UserDto {
        return newSuspendedTransaction {
            User.new {
                username = request.username
                password = BCrypt.withDefaults().hashToString(12, request.email.toCharArray())
                email = request.email
                picture = "no_avatar.png"
            }.toDto()
        }
    }

    data class CreateRequest(
        val username: String,
        val password: String,
        val email: String,
    )
}