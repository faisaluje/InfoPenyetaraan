package com.faisaluje.infopenyetaraan.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faisaluje.infopenyetaraan.BuildConfig
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.api.ApiPenyetaraan
import com.faisaluje.infopenyetaraan.model.Data
import com.faisaluje.infopenyetaraan.model.Profile
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment(), AnkoComponent<Context>{
    private val profile: MutableList<Profile> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var profileList: RecyclerView
    private lateinit var no: String
    private lateinit var adapter: ProfileAdapter

    companion object {
        fun newFragment(no: String): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.no = no

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolbar = activity?.find<Toolbar>(R.id.toolbar_profile)

        adapter = ProfileAdapter(profile)
        profileList.adapter = adapter

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL).build()
        val postApi = retrofit.create(ApiPenyetaraan::class.java)
        val response = postApi.getProfile(no)

        val call = postApi.getGuru(no)

//        println(call.request().url().toString())  // look url

        call.enqueue(object : Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val data = response.body()
                println(data)

                toolbar?.title = data?.guru?.nama
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
            profile.clear()
            profile.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            lparams(width = matchParent, height = matchParent)
            topPadding = dip(16)
            leftPadding = dip(8)
            rightPadding = dip(8)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                profileList = recyclerView {
                    id = R.id.rv_profile_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}