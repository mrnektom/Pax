package org.pax.messenger.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import org.pax.messenger.service.AccountsService
import org.pax.messenger.service.ApiService
import org.pax.messenger.service.AuthApi

@KoinViewModel
class LoginViewModel(
    private val apiService: ApiService,
    private val accountsService: AccountsService
) : ViewModel() {
    var state by mutableStateOf<LoginState>(LoginState.Idle)

    suspend fun login(
        host: String,
        username: String,
        password: String
    ) {
        runCatching {
            state = LoginState.HostCheck
            if (apiService.checkApiHost(host)) {
                state = LoginState.Login


            } else {
                state = LoginState.InvalidHost
            }

        }.onFailure {
            state = LoginState.Failure(it)
            it.printStackTrace()
        }
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object HostCheck : LoginState()
    data object Login : LoginState()
    data object Success : LoginState()

    data object InvalidHost : LoginState()
    data class Failure(val throwable: Throwable) : LoginState()
}