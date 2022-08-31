package com.lionparcel.commonandroidsample.badge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.badge.LPBadgeNumber
import com.lionparcel.commonandroidsample.R

class BadgeComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge_component_sample)
        findViewById<LPBadgeNumber>(R.id.lpBadgeNumber1).setNumber("5")
        findViewById<LPBadgeNumber>(R.id.lpBadgeNumber2).setNumber("5")
        findViewById<LPBadgeNumber>(R.id.lpBadgeNumber3).setNumber("99+")
        findViewById<LPBadgeNumber>(R.id.lpBadgeNumber4).setNumber("99+")
    }
}