package org.pax.messenger.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.pax.messenger.Database
import java.util.Properties

object DesktopDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:test.db", Properties(), Database.Schema.synchronous())
    }

}

actual fun getDriverFactory(): DriverFactory {
    return DesktopDriverFactory
}