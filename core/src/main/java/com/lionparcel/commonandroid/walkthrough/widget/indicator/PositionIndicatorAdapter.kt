package com.lionparcel.commonandroid.walkthrough.widget.indicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Space
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R

class PositionIndicatorAdapter(
    private val activeResource: Int,
    private val nonActiveResource: Int
) : RecyclerView.Adapter<PositionIndicatorAdapter.IndicatorViewHolder>() {

    private var indicatorQuantity = 1

    private var activeIndex: Int = 0

    class IndicatorViewHolder(val indicatorView: View) : RecyclerView.ViewHolder(indicatorView)

    override fun getItemCount(): Int = indicatorQuantity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.indicator_item_view, parent, false)
        return IndicatorViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        with(holder.indicatorView) {
            val space = findViewById<Space>(R.id.space)
            val ivIndicator = findViewById<ImageView>(R.id.ivIndicator)
            space.isVisible = position != 0
            ivIndicator.setImageResource(
                if (position == activeIndex) activeResource
                else nonActiveResource
            )
        }
    }

    fun setActivePosition(index: Int) {
        activeIndex = index
        notifyDataSetChanged()
    }

    fun setIndicatorQuantity(quantity: Int) {
        indicatorQuantity = quantity
        notifyDataSetChanged()
    }
}
