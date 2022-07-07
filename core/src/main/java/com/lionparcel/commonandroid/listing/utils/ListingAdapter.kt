package com.lionparcel.commonandroid.listing.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListingAdapter<T>(var items : List<T>) : RecyclerView.Adapter<ListingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        return ListingViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size
}