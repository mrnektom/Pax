package org.pax.messenger

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.pax.messenger.ui.navigation.NavGraph

@Composable
@Preview
fun App() {
    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = remember {
        if (isDarkTheme) darkColorScheme()
        else lightColorScheme()
    }
    MaterialTheme(
        colorScheme = colorScheme
    ) {

        NavGraph()
    }
}