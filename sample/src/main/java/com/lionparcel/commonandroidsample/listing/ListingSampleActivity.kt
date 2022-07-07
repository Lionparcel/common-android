package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_sample)

        findViewById<LPListing>(R.id.listing_1).listingAdapter(arrayListOf("JANUARY", "FEBRUARY", "MARCH"))
    }
}