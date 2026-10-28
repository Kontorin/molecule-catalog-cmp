package org.example.project

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var language by remember {
                mutableStateOf(if (Locale.getDefault().language == "en") "en" else "ru")
            }
            val configuration = Configuration(LocalConfiguration.current).apply {
                setLocale(Locale.forLanguageTag(language))
            }
            CompositionLocalProvider(LocalConfiguration provides configuration) {
                App(language = language, onLanguageChange = { selectedLanguage ->
                    Locale.setDefault(Locale.forLanguageTag(selectedLanguage))
                    language = selectedLanguage
                })
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
