package com.faisaluje.infopenyetaraan.guru

import com.faisaluje.infopenyetaraan.api.RetrofitFactory
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class GuruPresenter(private val view: GuruView,
                    private val retrofitFactory: RetrofitFactory){

    fun getData(nomor: String){
        view.showLoading()

        val service = retrofitFactory.makeRetrofitService()
        GlobalScope.launch(Dispatchers.Main){
            val request = service.getData(nomor)
            val response = request.await()
            if(response.isSuccessful){
                val data = response.body()
                if(data?.guru?.noBerkas.isNullOrEmpty()) view.showError("Guru tidak ditemukan") else view.showData(data!!.guru)
            }else{
                view.showError("Error ${response.message()}")
            }
            view.hideLoading()
        }
    }
}