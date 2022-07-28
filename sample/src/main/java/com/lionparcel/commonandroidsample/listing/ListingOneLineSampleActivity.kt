package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingOneLineSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_one_line_sample)
        findViewById<LPListing>(R.id.listing_1).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_rb).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_cb).apply {
            setListingTitle("January")
            setOnClickListener {
                checkBox.isChecked = true
            }
        }
        findViewById<LPListing>(R.id.listing_1_remove).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_ic).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_sw).apply {
            setListingTitle("January")
            setOnClickListener {
                switchEnd.isChecked = true
            }
        }
        findViewById<LPListing>(R.id.listing_1_re).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_ice).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_btn).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_icse).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_div).apply {
            setListingTitle("January")
        }
        findViewById<LPListing>(R.id.listing_1_sw1).apply {
            setListingTitle("January")
        }

    }
}
