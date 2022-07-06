package com.lionparcel.commonandroidsample.label

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.label.LPLabel
import com.lionparcel.commonandroidsample.R

class LabelMembershipSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_membership_sample)

        findViewById<LPLabel>(R.id.label_member_1).labelClickListener {
            finish()
        }
    }
}