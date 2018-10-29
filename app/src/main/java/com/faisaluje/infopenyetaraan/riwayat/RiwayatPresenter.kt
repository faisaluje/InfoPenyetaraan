package com.faisaluje.infopenyetaraan.riwayat

import com.faisaluje.infopenyetaraan.db.Riwayat
import com.faisaluje.infopenyetaraan.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class RiwayatPresenter(private val view: RiwayatActivity){
    fun getGuru(){

        view.database.use {
            view.showLoading()

            val result = select(Riwayat.TABLE_GURU)
            val data = result.parseList(classParser<com.faisaluje.infopenyetaraan.db.Riwayat>())

            view.showListGuru(data)

            view.hideLoading()
        }

    }
}