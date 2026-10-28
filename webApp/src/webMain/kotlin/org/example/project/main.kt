package org.example.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        var language by remember {
            mutableStateOf(if (Locale.current.language == "en") "en" else "ru")
        }
        App(language = language, onLanguageChange = { selectedLanguage ->
            updateBrowserLanguage(selectedLanguage)
            language = selectedLanguage
        })
    }
}

internal expect fun updateBrowserLanguage(language: String)
