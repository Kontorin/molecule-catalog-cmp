package org.example.project

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.rememberTooltipState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinproject.shared.generated.resources.Res
import kotlinproject.shared.generated.resources.catalog_title
import kotlinproject.shared.generated.resources.switch_to_dark_theme
import kotlinproject.shared.generated.resources.switch_to_light_theme
import kotlinproject.shared.generated.resources.theme_moon
import kotlinproject.shared.generated.resources.theme_sun
import org.jetbrains.compose.resources.painterResource
import kotlinproject.shared.generated.resources.switch_language
import org.example.project.appStringResource as stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(language: String = "", onLanguageChange: ((String) -> Unit)? = null) {
    val repository = remember { MockMoleculeRepository() }
    var darkTheme by remember { mutableStateOf(false) }

    val configuration = remember {
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.List::class, Route.List.serializer())
                    subclass(Route.Detail::class, Route.Detail.serializer())
                }
            }
        }
    }

    val backStack = rememberNavBackStack(configuration, Route.List)

    CompositionLocalProvider(LocalAppLanguage provides language) {
        MoleculeCatalogTheme(darkTheme = darkTheme) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(Res.string.catalog_title)) },
                        actions = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                if (onLanguageChange != null) {
                                    TextButton(onClick = {
                                        onLanguageChange(if (language == "en") "ru" else "en")
                                    }) {
                                        Text(stringResource(Res.string.switch_language))
                                    }
                                }
                                val themeAction = stringResource(
                                    if (darkTheme) Res.string.switch_to_light_theme
                                    else Res.string.switch_to_dark_theme,
                                )
                                TooltipBox(
                                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                                        TooltipAnchorPosition.Below,
                                    ),
                                    tooltip = { PlainTooltip { Text(themeAction) } },
                                    state = rememberTooltipState(),
                                ) {
                                    IconButton(onClick = { darkTheme = !darkTheme }) {
                                        Icon(
                                            painter = painterResource(
                                                if (darkTheme) Res.drawable.theme_sun
                                                else Res.drawable.theme_moon,
                                            ),
                                            contentDescription = themeAction,
                                        )
                                    }
                                }
                            }
                        },
                    )
                },
            ) { contentPadding ->
                NavDisplay(
                    backStack = backStack,
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator(),
                    ),
                    modifier = Modifier.fillMaxSize().padding(contentPadding),
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    },
                    transitionSpec = {
                        (slideInHorizontally { width -> width / 4 } + fadeIn()) togetherWith
                            (slideOutHorizontally { width -> -width / 8 } + fadeOut())
                    },
                    popTransitionSpec = {
                        (slideInHorizontally { width -> -width / 4 } + fadeIn()) togetherWith
                            (slideOutHorizontally { width -> width / 8 } + fadeOut())
                    },
                    entryProvider = entryProvider {
                        entry<Route.List> {
                            val catalogViewModel = viewModel { CatalogViewModel(repository) }
                            CatalogScreen(
                                state = catalogViewModel.state,
                                onQueryChange = catalogViewModel::onQueryChange,
                                onMoleculeClick = { id ->
                                    backStack.add(Route.Detail(id))
                                },
                            )
                        }

                        entry<Route.Detail> { route ->
                            val detailViewModel = viewModel {
                                MoleculeDetailViewModel(repository, route.id)
                            }
                            MoleculeDetailScreen(
                                state = detailViewModel.state,
                                onMoleculeClick = { id ->
                                    backStack.add(Route.Detail(id))
                                },
                                onBack = {
                                    backStack.removeLastOrNull()
                                },
                            )
                        }
                    },
                )
            }
        }
    }
}
