package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF006B5F),
    background = Color(0xFFF7FAF7),
    onBackground = Color(0xFF191C1B),
    surface = Color(0xFFF7FAF7),
    onSurface = Color(0xFF191C1B),
    onSurfaceVariant = Color(0xFF3F4946),
    outlineVariant = Color(0xFFBEC9C5),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF83D5C6),
    background = Color(0xFF101413),
    onBackground = Color(0xFFE0E3E1),
    surface = Color(0xFF101413),
    onSurface = Color(0xFFE0E3E1),
    onSurfaceVariant = Color(0xFFBEC9C5),
    outlineVariant = Color(0xFF3F4946),
)

@Composable
fun MoleculeCatalogTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
