package com.faisaluje.infopenyetaraan.model

import com.google.gson.annotations.SerializedName

data class Penolakan(
        @SerializedName("penolakan_id") val penolakanId: String?,
        @SerializedName("jenis_penolakan_id") val jenisPenolakanId: Int?,
        @SerializedName("dokumen_id") val dokumenId: String?,
        @SerializedName("keterangan") val keterangan: String?,
        @SerializedName("status") val status: Int?,
        @SerializedName("last_update") val lastUpdate: String?,
        @SerializedName("created_by") val createdBy: String?,
        @SerializedName("jenis_penolakan") val jenisPenolakan: String?,
        @SerializedName("verified") val verified: String?
)