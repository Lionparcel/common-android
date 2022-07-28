package com.lionparcel.commonandroidsample.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.listing.LPListing
import com.lionparcel.commonandroidsample.R

class ListingWithThumbnailSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_with_thumbnail_sample)
        findViewById<LPListing>(R.id.listing_3).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_rb).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_cb).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_remove).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_ic).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_sw).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_re).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_ice).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_btn).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_icse).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_div).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
        findViewById<LPListing>(R.id.listing_3_sw1).apply {
            setListingTitle("January")
            setListingSubtitle("2001")
            setListingThumbnail(R.drawable.image_blank_image)
        }
    }
}
