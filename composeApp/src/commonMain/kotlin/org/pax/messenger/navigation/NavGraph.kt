package org.pax.messenger.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf(Route.Main) }
    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Route.Main> {

            }
        },
        modifier = modifier
    )
}