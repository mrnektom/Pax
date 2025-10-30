package org.pax.messenger.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver

expect fun getWorker(): SqlDriver

object WebDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver = getWorker()
}

actual fun getDriverFactory(): DriverFactory = WebDriverFactory
