package org.example.project

data class MoleculeProperties(
    val alogp: String,
    val aromatic_rings: Int,
    val full_molformula: String,
    val full_mwt: String,
    val hba: Int,
    val hbd: Int,
    val heavy_atoms: Int,
    val mw_freebase: String,
    val np_likeness_score: String,
    val num_ro5_violations: Int,
    val psa: String,
    val qed_weighted: String,
    val ro3_pass: String,
    val rtb: Int
)