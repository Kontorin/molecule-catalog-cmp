package org.example.project

internal actual fun updateBrowserLanguage(language: String) {
    js("window.setAppLanguage(language)")
}
