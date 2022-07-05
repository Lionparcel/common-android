package com.lionparcel.commonandroidsample.label

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.R

class LabelSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_sample)

        findViewById<Button>(R.id.button_to_basicLabelType).setOnClickListener {
            startActivity(Intent(this, LabelBasicSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_productLabelType).setOnClickListener {
            startActivity(Intent(this, LabelProductSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_membershipLabelType).setOnClickListener {
            startActivity(Intent(this, LabelMembershipSampleActivity::class.java))
        }
    }
}