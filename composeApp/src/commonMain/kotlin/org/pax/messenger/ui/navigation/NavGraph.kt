package org.pax.messenger.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.koinInject
import org.pax.messenger.service.AccountsService
import org.pax.messenger.ui.screen.login.LoginScreen
import org.pax.messenger.ui.screen.main.MainScreen
import org.pax.messenger.ui.screen.register.RegisterScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val isAuthorized by koinInject<AccountsService>().isAuthorized.collectAsState()
    val backStack = remember(isAuthorized) {
        mutableStateListOf(
            if (isAuthorized) Route.Main
            else Route.Login
        )
    }

    val navigate = remember(backStack) {
        Navigate(
            onNavigate = { backStack += it },
            onNavigateBack = { backStack.removeLast() }
        )
    }

    CompositionLocalProvider(LocalNavigate provides navigate) {
        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider {
                entry<Route.Main> {
                    MainScreen()
                }

                entry<Route.Login> {
                    LoginScreen()
                }

                entry<Route.Register> {
                    RegisterScreen()
                }
            },
            modifier = modifier
        )
    }
}

fun interface OnNavigate {
    operator fun invoke(route: Route)
}

fun interface OnNavigateBack {
    operator fun invoke()
}

data class Navigate(
    val onNavigate: OnNavigate,
    val onNavigateBack: OnNavigateBack
)

val LocalNavigate = staticCompositionLocalOf<Navigate> { error("Navigate not provided") }