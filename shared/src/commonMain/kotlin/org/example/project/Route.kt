package org.example.project

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object List : Route

    @Serializable
    data class Detail(val id: String) : Route
}