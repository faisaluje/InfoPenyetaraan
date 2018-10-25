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
import com.faisaluje.infopenyetaraan.model.Guru
import com.faisaluje.infopenyetaraan.model.Profile
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ProfileFragment : Fragment(), AnkoComponent<Context>, ProfileView{
    private lateinit var guru: Guru
    private val profile: MutableList<Profile> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var profileList: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private lateinit var presenter: ProfilePresenter

    companion object {
        fun newFragment(guru: Guru): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.guru = guru

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ProfileAdapter(profile)
        profileList.adapter = adapter

        presenter = ProfilePresenter(this, guru)
        presenter.getProfile()

        swipeRefresh.onRefresh { presenter.getProfile() }
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

                profileList = recyclerView {
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

    override fun showListProfile(data: List<Profile>) {
        profile.clear()
        profile.addAll(data)
        adapter.notifyDataSetChanged()
    }
}