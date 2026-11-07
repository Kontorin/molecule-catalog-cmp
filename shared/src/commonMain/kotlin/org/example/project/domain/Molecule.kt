package org.example.project.domain

data class Molecule(
    val molecule_chembl_id: String,
    val pref_name: String,
    val formula: String,
    val molecularWeight: String,
    val parentChemblId: String,
)
