package com.lionparcel.commonandroidsample.tab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroidsample.R

class TabLayoutSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_sample)
        findViewById<Button>(R.id.button_to_tabTwoColumn).setOnClickListener {
            startActivity(Intent(this, TabTwoColumnSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_tabThreeColumn).setOnClickListener {
            startActivity(Intent(this, TabThreeColumnSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_tabScroll).setOnClickListener {
            startActivity(Intent(this, TabScrollSampleActivity::class.java))
        }
    }
}