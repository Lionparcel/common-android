package com.lionparcel.commonandroid.listing

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.listing.utils.ListingAdapter

class LPListing : LinearLayout {

    private val rvListing : RecyclerView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_listing, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPListing,
            0,
            0
        ).apply {
            try {

            } finally {
                recycle()
            }
        }

        rvListing = findViewById(R.id.rv_listing)
    }

    fun <T> listingAdapter(items : List<T>){
        val adapter = ListingAdapter(items)
        rvListing.adapter = adapter
        rvListing.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }
}