package org.pax.messenger.ui.screen.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<MainViewModel>()

    Text(viewModel.toString())
}