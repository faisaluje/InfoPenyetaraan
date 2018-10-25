package com.faisaluje.infopenyetaraan.dokumen

import com.faisaluje.infopenyetaraan.model.Dokumen

interface DokumenView{
    fun hideLoading()
    fun showLoading()
    fun showListDokumen(data: List<Dokumen>)
}