package com.faisaluje.infopenyetaraan.api

import com.faisaluje.infopenyetaraan.model.Profile
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPenyetaraan{

    @GET("infoJson/{noberkas}")
    fun getProfile(@Path("noberkas") noBerkas: String): Observable<List<Profile>>
}