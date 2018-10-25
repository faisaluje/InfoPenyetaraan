package com.faisaluje.infopenyetaraan.api

import com.faisaluje.infopenyetaraan.model.Data
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPenyetaraan{
    @GET("data/{no}")
    fun getData(@Path("no") no: String): Deferred<Response<Data>>
}