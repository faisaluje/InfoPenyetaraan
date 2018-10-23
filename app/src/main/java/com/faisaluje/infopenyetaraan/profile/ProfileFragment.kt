package com.faisaluje.infopenyetaraan.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.api.ApiPenyetaraan
import com.faisaluje.infopenyetaraan.model.Profile
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment(), AnkoComponent<Context>{
    private val profile: MutableList<Profile> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var profileList: RecyclerView
    private lateinit var noBerkas: String
    private lateinit var adapter: ProfileAdapter

    companion object {
        fun newFragment(noBerkas: String): ProfileFragment{
            val fragment = ProfileFragment()
            fragment.noBerkas = noBerkas

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ProfileAdapter(profile)
        profileList.adapter = adapter

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://simtara.gtk.kemdikbud.go.id:8000").build()
        val postApi = retrofit.create(ApiPenyetaraan::class.java)
        val response = postApi.getProfile(noBerkas)

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(){
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
            leftPadding = dip(16)
            rightPadding = dip(16)

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