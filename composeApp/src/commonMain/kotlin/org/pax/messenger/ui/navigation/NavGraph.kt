package org.pax.messenger.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.koinInject
import org.pax.messenger.service.AccountsService
import org.pax.messenger.ui.screen.login.LoginScreen
import org.pax.messenger.ui.screen.main.MainScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val isAuthorized by koinInject<AccountsService>().isAuthorized.collectAsState()
    val backStack = remember(isAuthorized) {
        mutableStateListOf(
            if (isAuthorized) Route.Main
            else Route.Login
        )
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Route.Main> {
                MainScreen()
            }

            entry<Route.Login> {
                LoginScreen()
            }
        },
        modifier = modifier
    )
}