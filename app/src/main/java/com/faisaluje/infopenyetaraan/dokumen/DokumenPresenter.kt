package com.faisaluje.infopenyetaraan.dokumen

import com.faisaluje.infopenyetaraan.model.Dokumen
import com.faisaluje.infopenyetaraan.model.Guru

class DokumenPresenter(private val view: DokumenView,
                       private val guru: Guru){
    private val dokumen: MutableList<Dokumen> = mutableListOf()

    fun getDokumen(){
        view.showLoading()

        if(guru.dokumen != null) dokumen.addAll(guru.dokumen)

        view.showListDokumen(dokumen)
        view.hideLoading()
    }
}