package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingTwoLineSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_two_line_sample)
        findViewById<LPListing>(R.id.listing_2).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_rb).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_cb).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_remove).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_ic).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_sw).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_re).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_ice).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_btn).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_icse).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_div).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
        findViewById<LPListing>(R.id.listing_2_sw1).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
        }
    }
}
