package com.lionparcel.services.consumer.view.common.widget.indicator

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.walkthrough.widget.indicator.PositionIndicatorAdapter

class PositionIndicatorView : FrameLayout {

    private var indicatorQuantity: Int = 1

    private var activeResource = R.drawable.bg_circle_interpack6

    private var nonActiveResource = R.drawable.bg_circle_shades2

    private var indicatorAdapter: PositionIndicatorAdapter? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        inflate(context, R.layout.position_indicator_view, this)
        val rvIndicator = findViewById<RecyclerView>(R.id.rvIndicator)
        indicatorAdapter = PositionIndicatorAdapter(
            activeResource,
            nonActiveResource
        )
        setQuantity(indicatorQuantity)

        with(rvIndicator) {
            adapter = indicatorAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(false)
        }

    }

    fun setActivePosition(index: Int) {
        indicatorAdapter?.setActivePosition(index)
    }

    fun setQuantity(quantity: Int) {
        indicatorAdapter?.setIndicatorQuantity(quantity)
    }
}
