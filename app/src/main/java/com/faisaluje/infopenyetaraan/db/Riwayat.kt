package com.faisaluje.infopenyetaraan.db

data class Riwayat(val ptkInpassingId: String?,
                   val noBerkas: String?,
                   val nuptk: String?,
                   val nama: String?,
                   val jenjangId: Int?
){
    companion object {
        const val TABLE_GURU = "TABLE_GURU"
        const val PTK_ID = "PTK_ID"
        const val NO_BERKAS = "NO_BERKAS"
        const val NUPTK = "NUPTK"
        const val JENJANG_ID = "JENJANG_ID"
        const val NAMA = "NAMA"
    }
}