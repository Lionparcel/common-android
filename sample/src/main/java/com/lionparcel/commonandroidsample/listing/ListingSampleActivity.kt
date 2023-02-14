package com.lionparcel.commonandroidsample.listing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.R

class ListingSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_sample)
        findViewById<Button>(R.id.btn_toListingOneLine).setOnClickListener {
            startActivity(Intent(this, ListingOneLineSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_toListingTwoLine).setOnClickListener {
            startActivity(Intent(this, ListingTwoLineSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_toListingWithThumbnail).setOnClickListener {
            startActivity(Intent(this, ListingWithThumbnailSampleActivity::class.java))
        }

    }
}