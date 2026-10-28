package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinproject.shared.generated.resources.Res
import kotlinproject.shared.generated.resources.catalog_title
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.catalog_title),
    ) {
        App()
    }
}
