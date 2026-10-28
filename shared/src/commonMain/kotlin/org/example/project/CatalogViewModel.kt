package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CatalogViewModel(repository: MoleculeRepository) : ViewModel() {
    private val allMolecules = repository.getMolecules()

    var state by mutableStateOf(CatalogState(molecules = allMolecules))
        private set

    fun onQueryChange(query: String) {
        state = CatalogState(
            query = query,
            molecules = allMolecules.filter { molecule ->
                molecule.pref_name.contains(query.trim(), ignoreCase = true)
            },
        )
    }
}
