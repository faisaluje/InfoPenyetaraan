package com.faisaluje.infopenyetaraan.riwayat

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.db.Riwayat
import com.faisaluje.infopenyetaraan.guru.GuruActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class RiwayatActivity: AppCompatActivity(), AnkoComponent<Context>, RiwayatView{
    private val riwayats: MutableList<Riwayat> = mutableListOf()
    private lateinit var adapter: RiwayatAdapter
    private lateinit var presenter: RiwayatPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listRiwayats: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(AnkoContext.create(this)))

        presenter = RiwayatPresenter(this)

        adapter = RiwayatAdapter(riwayats){
            startActivity<GuruActivity>("NOMOR" to it.nuptk)
        }
        listRiwayats.adapter = adapter

        swipeRefresh.onRefresh { presenter.getGuru() }

        presenter.getGuru()
    }

    override fun createView(ui: AnkoContext<Context>)= with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listRiwayats = recyclerView {
                        id = R.id.list_guru
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun showListGuru(data: List<Riwayat>) {
        riwayats.clear()
        riwayats.addAll(data)
        adapter.notifyDataSetChanged()
    }
}