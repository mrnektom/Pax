package org.pax.messenger.db

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.db.SqlDriver
import org.pax.messenger.Chats
import org.pax.messenger.Database
import org.pax.messenger.model.enumerated.ChatType
import kotlin.uuid.Uuid

interface DriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getDriverFactory(): DriverFactory

fun createDatabase(): Database {
    val driver = getDriverFactory().createDriver()
    val database = Database(
        driver,
        ChatsAdapter = Chats.Adapter(UUIDAdapter, EnumColumnAdapter())
    )

    return database
}


object UUIDAdapter : ColumnAdapter<Uuid, ByteArray> {
    override fun decode(databaseValue: ByteArray): Uuid = Uuid.fromByteArray(databaseValue)
    override fun encode(value: Uuid): ByteArray = value.toByteArray()
}
