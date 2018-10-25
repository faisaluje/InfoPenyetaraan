package com.faisaluje.infopenyetaraan.guru

import com.faisaluje.infopenyetaraan.model.Guru

interface GuruView{
    fun hideLoading()
    fun showLoading()
    fun showError(msg: String)
    fun showData(data: Guru)
}