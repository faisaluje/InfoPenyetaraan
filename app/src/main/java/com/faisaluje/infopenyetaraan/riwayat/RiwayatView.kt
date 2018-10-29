package com.faisaluje.infopenyetaraan.riwayat

import com.faisaluje.infopenyetaraan.db.Riwayat

interface RiwayatView{
    fun hideLoading()
    fun showLoading()
    fun showListGuru(data: List<Riwayat>)
}