package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingOneLineSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_one_line_sample)
        val id = arrayListOf(1, 2, 3)
        val month = arrayListOf("January", "February", "March")
        val year = arrayListOf("2001", "2002", "2003")
        val thumbnail = arrayListOf(R.drawable.image_blank_image, R.drawable.image_blank_image, R.drawable.image_blank_image)
        findViewById<LPListing>(R.id.listing_1).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_rb).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_cb).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_remove).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_ic).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_sw).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_re).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_ice).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_btn).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_icse).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_div).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_1_sw1).listingAdapter(id, month, year, thumbnail)

    }
}