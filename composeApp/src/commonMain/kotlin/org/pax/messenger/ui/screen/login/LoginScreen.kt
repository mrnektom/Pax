package org.pax.messenger.ui.screen.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.pax.messenger.ui.navigation.LocalNavigate
import org.pax.messenger.ui.navigation.Route

@Composable
fun LoginScreen() {
    val navigate = LocalNavigate.current

    val viewModel: LoginViewModel = koinViewModel()
    val scope = rememberCoroutineScope()

    val host = rememberTextFieldState()
    val login = rememberTextFieldState()
    val password = rememberTextFieldState()

    val isValid by remember {
        derivedStateOf {
            host.text.isNotBlank() && login.text.isNotBlank() && password.text.isNotBlank()
        }
    }


    val onLogin: () -> Unit = {
        scope.launch {
            viewModel.login(
                host.text.toString(),
                login.text.toString(),
                password.text.toString()
            )
        }
    }

    Scaffold {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text("Pax", style = MaterialTheme.typography.displayMedium)

            Column {

                OutlinedTextField(
                    state = host,
                    label = {
                        Text(text = "Messenger domain")
                    },
                    placeholder = {
                        Text(text = "Enter pax instance domain...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    state = login,
                    label = {
                        Text(text = "Email or login")
                    },
                    placeholder = {
                        Text(text = "Enter your email or login...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedSecureTextField(
                    state = password,
                    label = {
                        Text(text = "Password")
                    },
                    placeholder = {
                        Text(text = "Enter your password...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )


                AnimatedContent(viewModel.state) {
                    when (it) {
                        is LoginState.Failure -> Text("Failed to login ${it.throwable.message}")
                        LoginState.HostCheck -> Text("Check host...")
                        LoginState.Idle -> {}
                        LoginState.InvalidHost -> Text("Invalid host.")
                        LoginState.Login -> Text("Loading...")
                        LoginState.Success -> Text("Successfully logged in.")
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    enabled = isValid,
                    onClick = onLogin,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }

                TextButton(
                    onClick = { navigate.onNavigate(Route.Register) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    }
}