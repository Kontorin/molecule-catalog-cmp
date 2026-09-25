package org.example.project

interface MoleculeRepository {
    fun getMolecules(): List<Molecule>
    fun getMolecule(id: String): Molecule?
}

class MockMoleculeRepository : MoleculeRepository {
    override fun getMolecules(): List<Molecule> = mockMolecules
    override fun getMolecule(id: String): Molecule? =
        mockMolecules.find { it.molecule_chembl_id == id }
}