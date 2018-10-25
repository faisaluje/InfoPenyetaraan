package com.faisaluje.infopenyetaraan.dokumen

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
import com.faisaluje.infopenyetaraan.api.RetrofitFactory
import com.faisaluje.infopenyetaraan.guru.GuruActivity
import com.faisaluje.infopenyetaraan.guru.GuruPresenter
import com.faisaluje.infopenyetaraan.model.Dokumen
import com.faisaluje.infopenyetaraan.model.Guru
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DokumenFragment: Fragment(), AnkoComponent<Context>, DokumenView{
    private val dokumen: MutableList<Dokumen> = mutableListOf()
    private lateinit var guru: Guru
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var dokumenList: RecyclerView
    private lateinit var adapter: DokumenAdapter
    private lateinit var presenter: DokumenPresenter
    private lateinit var guruActivity: GuruActivity
    private lateinit var guruPresenter: GuruPresenter

    companion object {
        fun newFragment(guru: Guru, guruActivity: GuruActivity): DokumenFragment{
            val fragment = DokumenFragment()

            fragment.guru = guru
            fragment.guruActivity = guruActivity

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = DokumenAdapter(dokumen)
        dokumenList.adapter = adapter

        presenter = DokumenPresenter(this, guru)
        presenter.getDokumen()

        guruPresenter = GuruPresenter(guruActivity, RetrofitFactory)
        swipeRefresh.onRefresh {
            guruPresenter.getData(guru.noBerkas!!)
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

                dokumenList = recyclerView {
                    id = R.id.rv_profile_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun showListDokumen(data: List<Dokumen>) {
        dokumen.clear()
        dokumen.addAll(data)
        adapter.notifyDataSetChanged()
    }
}