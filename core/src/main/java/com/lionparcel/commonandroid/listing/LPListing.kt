package com.lionparcel.commonandroid.listing

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.listing.utils.ListData
import com.lionparcel.commonandroid.listing.utils.ListingAdapter

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPListing : LinearLayout {

    private var listingStyle : Int
    private var listDivider : Boolean
    private var iconStart : Int
    private var iconEnd : Int
    private var buttonText : String
    private var iconStartImage : Drawable
    private var iconEndImage : Drawable

    private val rvListing : RecyclerView
    private val listingAdapter by lazy {
        ListingAdapter(
            emptyList(),
            0,
            0,
            false,
            0,
            "",
            resources.getDrawable(R.drawable.ic_f_star),
            resources.getDrawable(R.drawable.ic_o_chevron_right)
        )
    }
    private var listData : ArrayList<ListData> = arrayListOf()

    private fun String?.setString() = this ?: ""

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
                listingStyle = getInt(R.styleable.LPListing_listStyle, 0)
                listDivider = getBoolean(R.styleable.LPListing_addListDivider, false)
                iconStart = getInt(R.styleable.LPListing_iconStart, 0)
                iconEnd = getInt(R.styleable.LPListing_iconEnd, 0)
                buttonText = getString(R.styleable.LPListing_buttonText).setString()
                iconStartImage = getDrawable(R.styleable.LPListing_iconStartImage)?: resources.getDrawable(R.drawable.ic_f_star)
                iconEndImage = getDrawable(R.styleable.LPListing_iconEndImage)?: resources.getDrawable(R.drawable.ic_o_chevron_right)
            } finally {
                recycle()
            }
        }

        rvListing = findViewById(R.id.rv_listing)

        rvListing.adapter = listingAdapter
        rvListing.layoutManager = LinearLayoutManager(context)
        rvListing.isNestedScrollingEnabled = false
        listingAdapter.listingStyle = listingStyle
        listingAdapter.iconStart = iconStart
        listingAdapter.listDivider = listDivider
        listingAdapter.iconEnd = iconEnd
        listingAdapter.buttonText = buttonText
        listingAdapter.iconStartImage = iconStartImage
        listingAdapter.iconEndImage = iconEndImage
    }

    fun listingAdapter(id: ArrayList<Int>, title : ArrayList<String>, subtitle : ArrayList<String>, thumbnail : ArrayList<Int>){
        for (i in 0 until id.count()){
            listData.add(ListData(title[i], subtitle[i], thumbnail[i]))
        }
        listingAdapter.items = listData
        listingAdapter.notifyDataSetChanged()
    }
}