package com.lionparcel.commonandroid.listing.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.lp_layout_listing_items.*

class ListingViewHolder (override val containerView : View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

        companion object {
            fun newInstance(parent : ViewGroup) : ListingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.lp_layout_listing_items, parent,false)
                return ListingViewHolder(view)
            }
        }

    fun <T> render(item : T){
        txt_listing_title.text = item.toString()
    }

}