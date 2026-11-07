package org.example.project.data

import org.example.project.domain.Molecule
import org.example.project.domain.MoleculeRepository

class MockMoleculeRepository : MoleculeRepository {
    override fun getMolecules(): List<Molecule> = mockMolecules
    override fun getMolecule(id: String): Molecule? =
        mockMolecules.find { it.molecule_chembl_id == id }

    override fun getParentMolecule(id: String): Molecule? {
        val molecule = getMolecule(id) ?: return null
        val parentId = molecule.parentChemblId
        if (parentId == id) return null
        return getMolecule(parentId)
    }
}
