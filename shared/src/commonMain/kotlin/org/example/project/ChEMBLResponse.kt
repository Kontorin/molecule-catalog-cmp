package org.example.project

data class ChEMBLResponse(
    val molecules: List<Molecule>,
    val page_meta: PageMeta
)