package com.faisaluje.infopenyetaraan.model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("success") val success: Boolean,
        @SerializedName("guru") val guru: Guru
)