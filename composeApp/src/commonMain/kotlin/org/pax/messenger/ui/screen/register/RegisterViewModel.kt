package org.pax.messenger.ui.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import org.pax.messenger.service.ApiService
import org.pax.messenger.ui.screen.login.LoginState

@KoinViewModel
class RegisterViewModel(
    private val apiService: ApiService
) : ViewModel() {
    var state by mutableStateOf<LoginState>(LoginState.Idle)

    suspend fun registerUser(
        host: String,
        email: String,
        username: String,
        password: String
    ) {
        runCatching {
            state = LoginState.HostCheck

            if (apiService.checkApiHost(host)) {
                state = LoginState.Login
                val api = apiService.getAuthApi(host)

                api.createUser(email, username, password)

                state = LoginState.Success
            } else {
                state = LoginState.InvalidHost
            }
        }.onFailure {
            state = LoginState.Failure(it)
        }
    }
}