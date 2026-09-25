package org.example.project

data class MoleculeHierarchy(
    val active_chembl_id: String,
    val molecule_chembl_id: String,
    val parent_chembl_id: String
)