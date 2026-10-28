package org.example.project

import androidx.lifecycle.ViewModel

class MoleculeDetailViewModel(repository: MoleculeRepository, id: String) : ViewModel() {
    val state = MoleculeDetailState(
        molecule = repository.getMolecule(id),
    )
}
