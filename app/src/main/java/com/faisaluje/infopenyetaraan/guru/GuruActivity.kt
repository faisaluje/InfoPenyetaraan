package com.faisaluje.infopenyetaraan.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.adapter.ViewPagerAdapter
import com.faisaluje.infopenyetaraan.berkas.BerkasFragment
import com.faisaluje.infopenyetaraan.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_profile.*

class GuruActivity : AppCompatActivity() {
    private lateinit var noBerkas: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        noBerkas = intent.getStringExtra("NOBERKAS")

        setSupportActionBar(toolbar_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(!noBerkas.isBlank()) supportActionBar?.title = noBerkas

        progress_bar_profile.visibility = View.VISIBLE
        viewpager_profile.visibility = View.GONE

        setupViewPager(viewpager_profile)

        tab_profile.setupWithViewPager(viewpager_profile)
    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(ProfileFragment.newFragment(noBerkas), "PROFIL")
        adapter.addFrag(BerkasFragment(), "BERKAS")
        viewPager.adapter = adapter
    }
}
