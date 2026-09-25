package org.example.project

data class MoleculeStructures(
    val canonical_smiles: String,
    val molfile: String,
    val standard_inchi: String,
    val standard_inchi_key: String
)