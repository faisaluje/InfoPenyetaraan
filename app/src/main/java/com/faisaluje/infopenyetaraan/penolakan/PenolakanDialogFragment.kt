package com.faisaluje.infopenyetaraan.penolakan

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.faisaluje.infopenyetaraan.model.Penolakan
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class PenolakanDialogFragment: DialogFragment(), AnkoComponent<Context>{
    private lateinit var penolakans: List<Penolakan>

    companion object {
        fun newDialogFragment(penolakans: List<Penolakan>): PenolakanDialogFragment{
            val fragment = PenolakanDialogFragment()
            fragment.penolakans = penolakans

            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = ctx.let { ctx ->
            AlertDialog.Builder(ctx)
                    .setView(createView(AnkoContext.create(ctx)))
                    .setPositiveButton("Oke", null)
                    .create()
        }

        return dialog
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL

            textView("Jenis ke 1"){
                textSize = 14f
                typeface = Typeface.DEFAULT_BOLD
            }.lparams(width = wrapContent, height = wrapContent)

            textView("Status : Valid"){

            }.lparams(width = wrapContent, height = wrapContent)
        }
    }
}