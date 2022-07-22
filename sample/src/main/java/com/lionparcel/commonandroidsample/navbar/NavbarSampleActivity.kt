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
    }
}