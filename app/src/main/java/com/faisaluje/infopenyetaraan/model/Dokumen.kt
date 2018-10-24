package com.faisaluje.infopenyetaraan.model

import com.google.gson.annotations.SerializedName

data class Dokumen(
        @SerializedName("dokumen_id") val dokumenId: String,
        @SerializedName("jenis_dokumen_id") val jenisDokumenId: Int,
        @SerializedName("ptk_inpassing_id") val ptkInpassingId: String,
        @SerializedName("no_dokumen") val noDokumen: String,
        @SerializedName("nama") val nama: String,
        @SerializedName("tgl_unggah") val tglUnggah: String,
        @SerializedName("tgl_dokumen") val tglDokumen: String,
        @SerializedName("verified") val verified: String,
        @SerializedName("last_update") val lastUpdate: String,
        @SerializedName("created_by") val createdBy: String,
        @SerializedName("jenis_dokumen") val jenisDokumen: String,
        @SerializedName("penolakan") val penolakan: List<Penolakan>
)