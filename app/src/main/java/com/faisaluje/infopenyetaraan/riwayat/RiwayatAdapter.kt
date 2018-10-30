package com.faisaluje.infopenyetaraan.riwayat

import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.db.Riwayat
import com.faisaluje.infopenyetaraan.db.database
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class RiwayatAdapter(private val riwayats: List<Riwayat>, private val onClick: (Riwayat) -> Unit, private val onLongClick: (Riwayat) -> Unit): RecyclerView.Adapter<RiwayatAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RiwayatUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = riwayats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(riwayats[position], onClick, onLongClick)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvNama: TextView = itemView.find(R.id.tv_nama)
        private val tvNuptk: TextView = itemView.find(R.id.tv_nuptk)
        private val tvNoBerkas: TextView = itemView.find(R.id.tv_no_berkas)
        private val database = itemView.context.database

        fun bindItem(riwayat: Riwayat, onClick: (Riwayat) -> Unit, onLongClick: (Riwayat) -> Unit){
            tvNama.text = riwayat.nama
            tvNuptk.text = riwayat.nuptk
            tvNoBerkas.text = riwayat.noBerkas

            itemView.onClick { onClick(riwayat) }

            itemView.onLongClick { onLongClick(riwayat) }
        }
    }

    class RiwayatUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                    margin = dip(5)
                    radius = 4f
                }
                isClickable = true
                isFocusable = true
                foreground = with(TypedValue()) {
                    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                    ContextCompat.getDrawable(context, resourceId)
                }

                relativeLayout {
                    padding = dip(6)

                    textView("Asep Bedil"){
                        id = R.id.tv_nama
                        textSize = 16f
                        textColor = Color.BLACK
                    }.lparams(width = wrapContent, height = wrapContent){
                        alignParentTop()
                    }

                    textView("NUPTK: "){
                        id = R.id.nuptk
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.tv_nama)
                    }

                    textView("28195723839481934"){
                        id = R.id.tv_nuptk
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.tv_nama)
                        rightOf(R.id.nuptk)
                    }

                    textView("No. Berkas: "){
                        id = R.id.no_berkas
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.nuptk)
                    }

                    textView("20140100011"){
                        id = R.id.tv_no_berkas
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.nuptk)
                        rightOf(R.id.no_berkas)
                    }
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottom = dip(4)
                }
            }
        }
    }
}