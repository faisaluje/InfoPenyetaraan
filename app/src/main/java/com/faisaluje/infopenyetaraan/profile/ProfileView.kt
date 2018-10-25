package com.faisaluje.infopenyetaraan.profile

import com.faisaluje.infopenyetaraan.model.Profile

interface ProfileView{
    fun hideLoading()
    fun showLoading()
    fun showListProfile(data: List<Profile>)
}