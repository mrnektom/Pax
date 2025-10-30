package org.pax.messenger

import android.app.Application

lateinit var application: Application

class PaxApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }
}