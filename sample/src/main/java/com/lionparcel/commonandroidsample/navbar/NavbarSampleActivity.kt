package com.lionparcel.commonandroidsample.navbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.R

class NavbarSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar_sample)
        findViewById<Button>(R.id.btn_navbar_ca).setOnClickListener {
            startActivity(Intent(this, NavbarCASampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_navbar_ca_long).setOnClickListener {
            startActivity(Intent(this, NavbarCALongSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_navbar_da).setOnClickListener {
            startActivity(Intent(this, NavbarDASampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_navbar_da_long).setOnClickListener {
            startActivity(Intent(this, NavbarDALongSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_navbar_ta).setOnClickListener {
            startActivity(Intent(this, NavbarTASampleActivity::class.java))
        }
    }
}