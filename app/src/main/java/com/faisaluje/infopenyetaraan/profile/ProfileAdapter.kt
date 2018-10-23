package com.faisaluje.infopenyetaraan.profile

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.model.Profile
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ProfileAdapter(private val profileList: List<Profile>): RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProfileUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = profileList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(profileList[position])

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title = itemView.find<TextView>(R.id.tv_title)
        private val value = itemView.find<TextView>(R.id.tv_value)

        fun bindItem(profil: Profile){
            title.text = profil.uraian
            value.text = profil.data
        }
    }

    class ProfileUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                    margin = dip(4)
                    radius = 4f
                }
                isClickable = true
                isFocusable = true
                foreground = with(TypedValue()) {
                    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
                    ContextCompat.getDrawable(context, resourceId)
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(6)

                    textView("NUPTK"){
                        id = R.id.tv_title
                        textSize = 12f
                    }.lparams(width = wrapContent, height = wrapContent)

                    textView("3736758660300002"){
                        id = R.id.tv_value
                        textSize = 18f
                        textColor = Color.BLACK
                        isSelectable = true
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottom = dip(4)
                }
            }
        }
    }
}