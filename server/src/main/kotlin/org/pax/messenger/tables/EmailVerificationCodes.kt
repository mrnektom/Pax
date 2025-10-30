package org.pax.messenger.tables

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.pax.messenger.model.enumerated.VerificationCodeType
import java.util.UUID

object EmailVerificationCodes : UUIDTable("email_verification_codes") {
    val email = varchar("email", 255)
    val code = varchar("code", 255)
    val type = enumeration<VerificationCodeType>("type")
}

class EmailVerificationCode(id: EntityID<UUID>) : UUIDEntity(id) {
    var email by EmailVerificationCodes.email
    var code by EmailVerificationCodes.code
    var type by EmailVerificationCodes.type

    companion object : UUIDEntityClass<EmailVerificationCode>(EmailVerificationCodes)
}
