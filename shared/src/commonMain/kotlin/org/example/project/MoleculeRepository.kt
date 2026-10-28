package org.example.project

interface MoleculeRepository {
    fun getMolecules(): List<Molecule>
    fun getMolecule(id: String): Molecule?
    fun getParentMolecule(id: String): Molecule?
}

class MockMoleculeRepository : MoleculeRepository {
    override fun getMolecules(): List<Molecule> = mockMolecules
    override fun getMolecule(id: String): Molecule? =
        mockMolecules.find { it.molecule_chembl_id == id }

    override fun getParentMolecule(id: String): Molecule? {
        val molecule = getMolecule(id) ?: return null
        val parentId = molecule.molecule_hierarchy.parentChemblId
        if (parentId == id) return null
        return getMolecule(parentId)
    }
}
