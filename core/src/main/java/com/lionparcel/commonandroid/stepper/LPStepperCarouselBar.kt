package com.lionparcel.commonandroid.stepper

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.viewpager.widget.ViewPager
import com.lionparcel.commonandroid.R

class LPStepperCarouselBar : LinearLayout {

    lateinit var lpViewPager: ViewPager
    private var indicatorMargin = dpToPx(3F)
    private var indicatorWidth = 0
    private var indicatorHeight = dpToPx(4F)
    private var indicatorBackgroundResId = R.drawable.bg_stepper_carousel_bar_active
    private var indicatorUnselectedBackgroundResId = R.drawable.bg_stepper_carousel_bar_inactive
    private var lastPosition = -1

    private val internalPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            if (lpViewPager.adapter == null || lpViewPager.adapter?.count ?: 0 <= 0) return
            if (lastPosition >= 0) getChildAt(lastPosition)?.setBackgroundResource(
                indicatorUnselectedBackgroundResId
            )
            getChildAt(position)?.setBackgroundResource(indicatorBackgroundResId)
            lastPosition = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    val dataObserver: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            val newCount = lpViewPager.adapter?.count ?: 0
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
            } finally {
                recycle()
            }
        }
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
    }

    fun setViewPager(viewPager: ViewPager) {
        this.lpViewPager = viewPager
        if (lpViewPager.adapter != null) {
            lastPosition = -1
            createIndicator()
            lpViewPager.removeOnPageChangeListener(internalPageChangeListener)
            lpViewPager.addOnPageChangeListener(internalPageChangeListener)
            internalPageChangeListener.onPageSelected(lpViewPager.currentItem)
        }
    }

    private fun createIndicator() {
        removeAllViews()
        val count = lpViewPager.adapter?.count ?: 0
        if (count <= 0) return

        val currentItem = lpViewPager.currentItem
        for (i in 0 until count) {
            if (currentItem == i) addIndicator(
                indicatorBackgroundResId,
                i
            )
            else addIndicator(indicatorUnselectedBackgroundResId, i)
        }
    }


    private fun addIndicator(@DrawableRes backgroundDrawableId: Int, index: Int) {
        val indicator = View(context)
        indicator.setBackgroundResource(backgroundDrawableId)
        indicator.setOnClickListener {
            lpViewPager.setCurrentItem(index, true)
        }
        addView(indicator, indicatorWidth, indicatorHeight)
        val lp = indicator.layoutParams as LayoutParams
        lp.weight = 1F
        lp.leftMargin = indicatorMargin
        lp.rightMargin = indicatorMargin
        indicator.layoutParams = lp
    }

    private fun dpToPx(dpValue: Float): Int =
        (dpValue * resources.displayMetrics.density + 0.5f).toInt()

}