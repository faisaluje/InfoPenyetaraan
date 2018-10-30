package com.faisaluje.infopenyetaraan.riwayat

import android.app.AlertDialog
import android.content.Context
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.db.Riwayat
import com.faisaluje.infopenyetaraan.db.database
import com.faisaluje.infopenyetaraan.guru.GuruActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.delete
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Riwayat pencarian"

        presenter = RiwayatPresenter(this)

        adapter = RiwayatAdapter(riwayats,{
            startActivity<GuruActivity>("NOMOR" to it.nuptk)
        }){
            onDelete(it.nuptk)
        }
        listRiwayats.adapter = adapter

        swipeRefresh.onRefresh { presenter.getGuru() }

        presenter.getGuru()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.riwayat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_clear_all -> {
                onDelete()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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

    private fun onDelete(nuptk: String? = null){
        if(riwayats.size < 1) return Toast.makeText(this, "Tidak Ada yang Dihapus", Toast.LENGTH_SHORT).show()

        val dialog = AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Anda yakin akan menghapusnya?")
                .setPositiveButton("YA"){ _, _ ->
                    var result = 0
                    try {
                        database.use {
                            if(!nuptk.isNullOrEmpty()) {
                                result = delete(Riwayat.TABLE_GURU, "NUPTK = {nuptk}", "nuptk" to nuptk!!)
                            }else{
                                result = delete(Riwayat.TABLE_GURU)
                            }
                        }
                    }catch (e: SQLiteException){
                        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                    if(result > 0) Toast.makeText(this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "Tidak Ada yang Dihapus", Toast.LENGTH_SHORT).show()

                    presenter.getGuru()
                }
                .setNegativeButton("TIDAK"){ _, _ ->  }
                .create()

        dialog.show()
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