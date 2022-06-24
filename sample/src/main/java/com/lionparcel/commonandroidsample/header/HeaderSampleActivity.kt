package com.lionparcel.commonandroidsample.header

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.R

class HeaderSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_sample)

        findViewById<Button>(R.id.btn_header_ca).setOnClickListener {
            startActivity(Intent(this, HeaderCASampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_header_da).setOnClickListener {
            startActivity(Intent(this, HeaderDASampleActivity::class.java))
        }
    }
}