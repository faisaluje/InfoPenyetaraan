package com.faisaluje.infopenyetaraan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val version = BuildConfig.VERSION_NAME

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_title.text = getString(R.string.app_name) +" v${version}"

        card_scan.setOnClickListener { onScan() }
        card_input.setOnClickListener { onInput() }

//        btn_scan.setOnClickListener { onScan() }
//        btn_input.setOnClickListener { onInput() }
    }

    private fun onScan(){
        val intent = Intent(this@MainActivity, ScannerActivity::class.java)

        askPermission(){
            startActivity(intent)
        }.onDeclined { e -> }
    }

    private fun onInput(){
        MaterialDialog(this)
                .title(text = "No. Berkas")
                .input(inputType = InputType.TYPE_CLASS_NUMBER) { _, text ->
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                }
                .positiveButton(R.string.search)
                .show()
    }
}
