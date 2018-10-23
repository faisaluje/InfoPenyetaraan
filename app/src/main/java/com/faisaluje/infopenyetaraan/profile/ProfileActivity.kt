package com.faisaluje.infopenyetaraan.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var noBerkas: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        noBerkas = intent.getStringExtra("NOBERKAS")

        setSupportActionBar(toolbar_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(!noBerkas.isBlank()) supportActionBar?.title = noBerkas

        setupViewPager(viewpager_profile)

        tab_profile.setupWithViewPager(viewpager_profile)
    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(ProfileFragment.newFragment(noBerkas), "PROFIL")
        adapter.addFrag(ProfileFragment.newFragment(noBerkas), "BERKAS")
        viewPager.adapter = adapter
    }
}
