package org.pax.messenger.service

import io.ktor.server.plugins.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.pax.messenger.helpers.passwordVerify
import org.pax.messenger.helpers.randomString
import org.pax.messenger.tables.Token
import org.pax.messenger.tables.User
import org.pax.messenger.tables.Users
import org.pax.messenger.tables.toDto
import java.time.Duration
import java.time.Instant

class TokensService {
    suspend fun createToken(username: String, password: String) = newSuspendedTransaction {
        val user = User.find { Users.username eq username }.firstOrNull()
            ?: throw NotFoundException()

        if(!password.passwordVerify(user.password)) throw BadRequestException("Wrong password")

        Token.new {
            accessToken = randomString()
            refreshToken = randomString()
            expiresIn = Instant.now().plus(Duration.ofDays(7))
        }.toDto()
    }
}