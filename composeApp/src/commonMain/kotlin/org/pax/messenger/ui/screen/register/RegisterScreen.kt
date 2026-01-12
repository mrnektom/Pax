package org.pax.messenger.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pax.messenger.ui.components.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    val host = rememberTextFieldState()
    val email = rememberTextFieldState()
    val login = rememberTextFieldState()
    val password = rememberTextFieldState()
    val secondPassword = rememberTextFieldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Register")
                },
                navigationIcon = {
                    BackButton()
                }
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .widthIn(max = 400.dp)
                .padding(horizontal = 16.dp),
        ) {
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
                state = email,
                label = {
                    Text(text = "Email")
                },
                placeholder = {
                    Text(text = "Enter your email or login...")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                state = login,
                label = {
                    Text(text = "Username")
                },
                placeholder = {
                    Text(text = "Enter your username...")
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
            OutlinedSecureTextField(
                state = secondPassword,
                label = {
                    Text(text = "Repeat password")
                },
                placeholder = {
                    Text(text = "Repeat your password...")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(

            )

            Button(
                onClick = {}
            ) {
                Text("Register")
            }
        }
    }
}