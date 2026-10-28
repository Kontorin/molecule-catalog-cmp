package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinproject.shared.generated.resources.Res
import kotlinproject.shared.generated.resources.entries_in_catalog
import kotlinproject.shared.generated.resources.formula
import kotlinproject.shared.generated.resources.library_title
import kotlinproject.shared.generated.resources.nothing_found
import kotlinproject.shared.generated.resources.search_by_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun CatalogScreen(
    state: CatalogState,
    onQueryChange: (String) -> Unit,
    onMoleculeClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            OutlinedTextField(
                value = state.query,
                onValueChange = onQueryChange,
                label = { Text(stringResource(Res.string.search_by_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item {
            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    text = stringResource(Res.string.library_title),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "${state.molecules.size} ${stringResource(Res.string.entries_in_catalog)}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        if (state.molecules.isEmpty()) {
            item {
                Text(
                    text = stringResource(Res.string.nothing_found),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        items(
            items = state.molecules,
            key = { molecule -> molecule.molecule_chembl_id },
        ) { molecule ->
            Card(
                onClick = { onMoleculeClick(molecule.molecule_chembl_id) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = molecule.pref_name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = molecule.molecule_chembl_id,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Text(
                        text = "${stringResource(Res.string.formula)}: ${molecule.molecule_properties.full_molformula}",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}
