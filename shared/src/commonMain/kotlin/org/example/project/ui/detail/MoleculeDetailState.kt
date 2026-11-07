package org.example.project.ui.detail

import org.example.project.domain.Molecule

data class MoleculeDetailState(
    val molecule: Molecule?,
    val parentMolecule: Molecule?,
)
