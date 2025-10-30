package org.pax.messenger.service

import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.pax.messenger.helpers.randomString
import org.pax.messenger.model.enumerated.VerificationCodeType
import org.pax.messenger.tables.EmailVerificationCode

class EmailVerificationCodesService {
    suspend fun create(
        email: String,
        type: VerificationCodeType
    ): EmailVerificationCode {

        return newSuspendedTransaction {
            EmailVerificationCode.new {
                this.email = email
                this.code = randomString(5)
                this.type = type
            }
        }
    }
}