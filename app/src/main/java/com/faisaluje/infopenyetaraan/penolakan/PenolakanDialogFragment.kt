package com.faisaluje.infopenyetaraan.penolakan

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faisaluje.infopenyetaraan.model.Penolakan
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class PenolakanDialogFragment: DialogFragment(), AnkoComponent<Context>{
    private lateinit var penolakans: List<Penolakan>
    private lateinit var penolakanList: RecyclerView
    private lateinit var adapter: PenolakanAdapter

    companion object {
        fun newDialogFragment(penolakans: List<Penolakan>): PenolakanDialogFragment{
            val fragment = PenolakanDialogFragment()
            fragment.penolakans = penolakans

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = PenolakanAdapter(penolakans)
        penolakanList.adapter = adapter
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
            topPadding = dip(16)
            leftPadding = dip(8)
            rightPadding = dip(8)

            penolakanList = recyclerView {
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}