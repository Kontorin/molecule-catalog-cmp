package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.Locale
import kotlinproject.shared.generated.resources.Res
import kotlinproject.shared.generated.resources.catalog_title
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    var language by remember {
        mutableStateOf(if (Locale.getDefault().language == "en") "en" else "ru")
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = key(language) { stringResource(Res.string.catalog_title) },
    ) {
        App(language = language, onLanguageChange = { selectedLanguage ->
            Locale.setDefault(Locale.forLanguageTag(selectedLanguage))
            language = selectedLanguage
        })
    }
}
