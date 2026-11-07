package org.example.project.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal val LocalAppLanguage = staticCompositionLocalOf { "" }

// Recreate only the string resource state when the app locale changes.
@Composable
internal fun appStringResource(resource: StringResource): String =
    key(LocalAppLanguage.current) {
        stringResource(resource)
    }
