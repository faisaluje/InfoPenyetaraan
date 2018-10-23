package com.faisaluje.infopenyetaraan.model

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("nu") val nu: String,
    @SerializedName("no") val no: String,
    @SerializedName("uraian") val uraian: String,
    @SerializedName("data") val data: String,
    @SerializedName("keterangan") val keterangan: String
)