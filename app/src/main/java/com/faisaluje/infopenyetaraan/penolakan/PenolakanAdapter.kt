package com.faisaluje.infopenyetaraan.penolakan

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.model.Penolakan
import org.jetbrains.anko.*

class PenolakanAdapter(private val penolakans: List<Penolakan>): RecyclerView.Adapter<PenolakanAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PenolakanUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = penolakans.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(penolakans[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val namaPenolakan: TextView = itemView.find(R.id.tv_penolakan)
        private val alasanPenolakan: TextView = itemView.find(R.id.tv_alasan_penolakan)

        fun bindItem(penolakan: Penolakan){
            namaPenolakan.text = penolakan.jenisPenolakan
            alasanPenolakan.text = penolakan.keterangan
        }
    }

    class PenolakanUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(6)

                textView("- "){
                    id = R.id.tv_strip
                    textSize = 14f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent){
                    alignParentTop()
                    alignParentLeft()
                }

                textView("Jenis ke 1"){
                    id = R.id.tv_penolakan
                    textSize = 14f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent){
                    alignParentTop()
                    rightOf(R.id.tv_strip)
                }

                textView("Status : Valid"){
                    id = R.id.tv_alasan_penolakan
                }.lparams(width = wrapContent, height = wrapContent){
                    rightOf(R.id.tv_strip)
                    bottomOf(R.id.tv_penolakan)
                }
            }
        }
    }
}