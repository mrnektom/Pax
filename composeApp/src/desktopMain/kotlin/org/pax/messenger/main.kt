package org.pax.messenger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import  org.koin.ksp.generated.startKoin

fun main() = application {
    Application.startKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Pax",
    ) {
        App()
    }
}