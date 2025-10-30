package org.pax.messenger.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.pax.messenger.Database
import org.pax.messenger.application

object AndroidDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema.synchronous(), application, "database.db")
    }
}

actual fun getDriverFactory(): DriverFactory = AndroidDriverFactory