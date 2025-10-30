package org.pax.messenger.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Route : NavKey {

    @Serializable
    data object Main : Route()

    @Serializable
    data object Login : Route()
}