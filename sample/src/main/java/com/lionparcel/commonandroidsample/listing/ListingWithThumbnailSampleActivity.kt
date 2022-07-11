package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingWithThumbnailSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_with_thumbnail_sample)
        val id = arrayListOf(1, 2, 3)
        val month = arrayListOf("January", "February", "March")
        val year = arrayListOf("2001", "2002", "2003")
        val thumbnail = arrayListOf(R.drawable.image_blank_image, R.drawable.image_blank_image, R.drawable.image_blank_image)
        findViewById<LPListing>(R.id.listing_3).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_rb).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_cb).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_remove).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_ic).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_sw).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_re).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_ice).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_btn).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_icse).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_div).listingAdapter(id, month, year, thumbnail)
        findViewById<LPListing>(R.id.listing_3_sw1).listingAdapter(id, month, year, thumbnail)
    }
}