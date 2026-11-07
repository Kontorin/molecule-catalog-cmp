package org.example.project.domain

interface MoleculeRepository {
    fun getMolecules(): List<Molecule>
    fun getMolecule(id: String): Molecule?
    fun getParentMolecule(id: String): Molecule?
}

