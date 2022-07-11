package com.lionparcel.commonandroid.listing.utils

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListingAdapter(
    var items: List<ListData>,
    var listingStyle: Int,
    var iconStart: Int,
    var listDivider: Boolean,
    var iconEnd: Int,
    var buttonText: String,
    var iconStartImage: Drawable,
    var iconEndImage: Drawable
) :
    RecyclerView.Adapter<ListingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        return ListingViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.render(
            items[position],
            listingStyle,
            iconStart,
            listDivider,
            iconEnd,
            buttonText,
            iconStartImage,
            iconEndImage
        )
    }

    override fun getItemCount(): Int = items.size
}