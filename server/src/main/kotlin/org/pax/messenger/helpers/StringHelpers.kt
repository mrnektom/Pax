package org.pax.messenger.helpers

import java.security.SecureRandom
import kotlin.random.Random

private const val alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_(){}[]+=*&?^%$#@!~`/\\|@"
private val secureRandom = SecureRandom()

fun randomString(len: Int = 20): String = buildString(len) {
    repeat(len) {
        append(alphabet[secureRandom.nextInt(alphabet.length)])
    }
}
