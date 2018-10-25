package com.faisaluje.infopenyetaraan.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.faisaluje.infopenyetaraan.R
import com.faisaluje.infopenyetaraan.adapter.ViewPagerAdapter
import com.faisaluje.infopenyetaraan.api.RetrofitFactory
import com.faisaluje.infopenyetaraan.berkas.BerkasFragment
import com.faisaluje.infopenyetaraan.model.Guru
import com.faisaluje.infopenyetaraan.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_guru.*

class GuruActivity : AppCompatActivity(), GuruView {
    private lateinit var noBerkas: String
    private lateinit var presenter: GuruPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru)

        noBerkas = intent.getStringExtra("NOBERKAS")

        setSupportActionBar(toolbar_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(!noBerkas.isBlank()) supportActionBar?.title = noBerkas else finish()

        presenter = GuruPresenter(this, RetrofitFactory)
        presenter.getData(noBerkas)
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progress_bar_profile.visibility = View.VISIBLE
        viewpager_profile.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar_profile.visibility = View.GONE
        viewpager_profile.visibility = View.VISIBLE
    }

    override fun showData(data: Guru) {
        supportActionBar?.title = data.nama
        setupViewPager(viewpager_profile, data)
        tab_profile.setupWithViewPager(viewpager_profile)
    }

    private fun setupViewPager(viewPager: ViewPager, guru: Guru){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(ProfileFragment.newFragment(guru), "PROFIL")
        adapter.addFrag(BerkasFragment(), "BERKAS")
        viewPager.adapter = adapter
    }
}
