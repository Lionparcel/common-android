package com.lionparcel.commonandroidsample.stepper.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroidsample.R

class ViewPager2Adapter(private val listNumber: List<String>): RecyclerView.Adapter<ViewPager2Adapter.ImageViewHolder>() {

    private val eventList = listOf(listNumber.last()) + listNumber + listOf(listNumber.first())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder = ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_demo_bar_view_pager_2, parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        (holder.view as TextView).also {
            it.text = "Page " + eventList.get(position)

            val backgroundResId = if (position % 2 == 0) R.color.aqua5 else R.color.yellow4
            it.setBackgroundColor(ContextCompat.getColor(it.context, backgroundResId))
        }
    }

    override fun getItemCount(): Int = eventList.size

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}