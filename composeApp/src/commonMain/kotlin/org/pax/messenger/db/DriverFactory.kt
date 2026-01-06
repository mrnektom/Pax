package org.pax.messenger.db

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import org.koin.core.annotation.Single
import org.pax.messenger.Account
import org.pax.messenger.Chats
import org.pax.messenger.Database
import kotlin.uuid.Uuid

interface DriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getDriverFactory(): DriverFactory

@Single
fun createDatabase(): Database {
    val driver = getDriverFactory().createDriver()
    val database = Database(
        driver,
        ChatsAdapter = Chats.Adapter(UUIDAdapter, EnumColumnAdapter()),
        AccountAdapter = Account.Adapter(UUIDAdapter)
    )

    return database
}


object UUIDAdapter : ColumnAdapter<Uuid, ByteArray> {
    override fun decode(databaseValue: ByteArray): Uuid = Uuid.fromByteArray(databaseValue)
    override fun encode(value: Uuid): ByteArray = value.toByteArray()
}
