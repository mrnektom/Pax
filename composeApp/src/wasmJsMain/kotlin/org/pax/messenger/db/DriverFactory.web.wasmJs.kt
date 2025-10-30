package org.pax.messenger.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker
import org.w3c.dom.url.URL

@OptIn(ExperimentalWasmJsInterop::class)
actual fun getWorker(): SqlDriver {

    return  WebWorkerDriver(Worker(workerUrl().toString()))
}

fun workerUrl(): JsAny = js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")