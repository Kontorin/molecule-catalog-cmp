package org.example.project.ui.catalog

import org.example.project.domain.Molecule

data class CatalogState(
    val molecules: List<Molecule>,
    val query: String = "",
)
