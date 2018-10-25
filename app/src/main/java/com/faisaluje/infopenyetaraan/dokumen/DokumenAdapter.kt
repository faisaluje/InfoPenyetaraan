package com.faisaluje.infopenyetaraan.dokumen

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
import com.faisaluje.infopenyetaraan.model.Dokumen
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk27.coroutines.onClick

class DokumenAdapter(private val dokumen: List<Dokumen>, private val listener: (Dokumen) -> Unit): RecyclerView.Adapter<DokumenAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DokumenUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = dokumen.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dokumen[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val namaDokumen: TextView = itemView.find(R.id.tv_dokumen)
        private val status: TextView = itemView.find(R.id.tv_status)

        fun bindItem(dokumen: Dokumen, listener: (Dokumen) -> Unit){
            namaDokumen.text = dokumen.jenisDokumen
            status.text = dokumen.verified
            if(dokumen.verified.equals("Valid")){
                status.textColor = Color.rgb(114, 143, 2)
            }else if(dokumen.verified.equals("Peringatan")){
                status.textColor = Color.rgb(203, 157, 6)
            }else if(dokumen.verified.equals("Fatal")){
                status.textColor = Color.rgb(132, 0, 0)
            }

            itemView.onClick { listener(dokumen) }
        }
    }

    class DokumenUI: AnkoComponent<ViewGroup>{
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

                    textView("Ijazah S1"){
                        id = R.id.tv_dokumen
                        textSize = 16f
                        textColor = Color.BLACK
                    }.lparams(width = wrapContent, height = wrapContent){
                        alignParentTop()
                    }

                    textView("Status: "){
                        id = R.id.status
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.tv_dokumen)
                    }

                    textView("Valid"){
                        id = R.id.tv_status
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent){
                        bottomOf(R.id.tv_dokumen)
                        rightOf(R.id.status)
                    }
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottom = dip(4)
                }
            }
        }
    }
}