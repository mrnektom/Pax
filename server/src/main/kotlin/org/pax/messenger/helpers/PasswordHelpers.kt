package org.pax.messenger.helpers

import at.favre.lib.crypto.bcrypt.BCrypt

fun String.passwordHash() = BCrypt.withDefaults().hashToString(12, toCharArray())

fun String.passwordVerify(hash: String) = BCrypt.verifyer().verify(toCharArray(), hash).verified