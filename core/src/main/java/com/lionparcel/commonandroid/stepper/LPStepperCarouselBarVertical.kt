package com.lionparcel.commonandroid.stepper

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.lionparcel.commonandroid.R

class LPStepperCarouselBarVertical : LinearLayout {

    lateinit var lpViewPager: ViewPager2
    private var indicatorMargin = dpToPx(0F)
    private var indicatorWidth = dpToPx(4F)
    private var indicatorHeight = 0
    private var indicatorBackgroundResId = R.drawable.bg_stepper_carousel_bar_active
    private var indicatorUnselectedBackgroundResId = R.drawable.bg_stepper_carousel_bar_inactive
    private var lastPosition = -1
    private var isBarClickable: Boolean = true

    private val internalPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) {
            if (lpViewPager.adapter == null || lpViewPager.adapter?.itemCount ?: 0 <= 0) return
            if (lastPosition >= 0) getChildAt(lastPosition)?.setBackgroundResource(
                indicatorUnselectedBackgroundResId
            )
            getChildAt(position)?.setBackgroundResource(indicatorBackgroundResId)
            lastPosition = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            val newCount = lpViewPager.adapter?.itemCount ?: 0
            val currentCount = childCount
            lastPosition = when {
                newCount == currentCount -> return
                lastPosition < newCount -> lpViewPager.currentItem
                else -> -1
            }
            createIndicator()
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LPStepperCarouselBar, 0, 0)
            .apply {
                try {
                    indicatorHeight = getDimensionPixelSize(R.styleable.LPStepperCarouselBar_barHeight, indicatorHeight)
                    indicatorMargin = getDimensionPixelSize(R.styleable.LPStepperCarouselBar_barSpacing, indicatorMargin)
                    indicatorBackgroundResId = getResourceId(R.styleable.LPStepperCarouselBar_barBackgroundActive, indicatorBackgroundResId)
                    indicatorUnselectedBackgroundResId = getResourceId(R.styleable.LPStepperCarouselBar_barBackgroundInactive, indicatorUnselectedBackgroundResId)
                    isBarClickable = getBoolean(R.styleable.LPStepperCarouselBar_isBarClickable, isBarClickable)
                } finally {
                    recycle()
                }
            }
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    fun setViewPager(viewPager: ViewPager2) {
        if (viewPager.adapter == null) {
            throw IllegalStateException(
                "You have to set an adapter to the view pager before " +
                        "initializing the stepper carousel bar !"
            )
        }
        this.lpViewPager = viewPager
        if (lpViewPager.adapter != null) {
            lastPosition = -1
            createIndicator()
            lpViewPager.unregisterOnPageChangeCallback(internalPageChangeListener)
            lpViewPager.registerOnPageChangeCallback(internalPageChangeListener)
            internalPageChangeListener.onPageSelected(lpViewPager.currentItem)
            lpViewPager.adapter!!.registerAdapterDataObserver(dataObserver)
        }
    }

    private fun createIndicator() {
        removeAllViews()
        val count = lpViewPager.adapter?.itemCount ?: 0
        if (count <= 0) return

        val currentItem = lpViewPager.currentItem
        for (i in 0 until count) {
            if (currentItem == i) addIndicator(
                indicatorBackgroundResId,
                i,
                count
            )
            else addIndicator(indicatorUnselectedBackgroundResId, i, count)
        }
    }


    private fun addIndicator(@DrawableRes backgroundDrawableId: Int, index: Int, maxIndicator: Int) {
        val indicator = View(context)
        indicator.setBackgroundResource(backgroundDrawableId)
        if (isBarClickable) {
            indicator.setOnClickListener {
                lpViewPager.setCurrentItem(index, true)
            }
        }
        addView(indicator, indicatorWidth, indicatorHeight)
        val lp = indicator.layoutParams as LayoutParams
        lp.weight = 1F
        if (index+1 != maxIndicator) lp.bottomMargin = indicatorMargin
        indicator.layoutParams = lp
    }

    private fun dpToPx(dpValue: Float): Int =
        (dpValue * resources.displayMetrics.density + 0.5f).toInt()

}