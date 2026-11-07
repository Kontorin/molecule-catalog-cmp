package org.example.project

@OptIn(kotlin.js.ExperimentalWasmJsInterop::class)
internal actual fun updateBrowserLanguage(language: String) {
    js("window.setAppLanguage(language)")
}
