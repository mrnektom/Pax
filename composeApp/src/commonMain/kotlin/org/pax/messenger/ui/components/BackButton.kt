package org.pax.messenger.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pax.messenger.ui.navigation.LocalNavigate

@Composable
fun BackButton(modifier: Modifier = Modifier) {
    val navigate = LocalNavigate.current

    IconButton(
        onClick = { navigate.onNavigateBack() },
    ) {
        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
    }
}