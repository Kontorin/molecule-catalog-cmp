package org.example.project.ui.detail

import org.example.project.domain.MoleculeRepository

import androidx.lifecycle.ViewModel

class MoleculeDetailViewModel(repository: MoleculeRepository, id: String) : ViewModel() {
    val state = MoleculeDetailState(
        molecule = repository.getMolecule(id),
        parentMolecule = repository.getParentMolecule(id),
    )
}
