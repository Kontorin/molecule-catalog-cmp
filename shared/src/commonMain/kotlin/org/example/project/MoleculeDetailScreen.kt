package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import kotlinproject.shared.generated.resources.Res
import kotlinproject.shared.generated.resources.back
import kotlinproject.shared.generated.resources.formula
import kotlinproject.shared.generated.resources.molecular_properties
import kotlinproject.shared.generated.resources.molecular_weight
import kotlinproject.shared.generated.resources.molecule_not_found
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoleculeDetailScreen(
    molecule: Molecule?,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        TextButton(onClick = onBack) {
            Text("← ${stringResource(Res.string.back)}")
        }

        if (molecule == null) {
            Text(
                text = stringResource(Res.string.molecule_not_found),
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = molecule.pref_name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = molecule.molecule_chembl_id,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontFamily = FontFamily.Monospace,
                    )

                    HorizontalDivider()

                    Text(
                        text = stringResource(Res.string.molecular_properties),
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = "${stringResource(Res.string.formula)}: ${molecule.molecule_properties.full_molformula}",
                    )
                    Text(
                        text = "${stringResource(Res.string.molecular_weight)}: ${molecule.molecule_properties.full_mwt} Da",
                    )
                }
            }
        }
    }
}
