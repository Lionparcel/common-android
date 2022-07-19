package com.lionparcel.commonandroid.stepper.utils

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.lionparcel.commonandroid.R

abstract class BaseCarouselIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @JvmField
    protected val dots = ArrayList<ImageView>()

    private var dotColor : Int
    private var dotSize: Int
    private var dotCornerRadius: Int
    private var dotSpacing: Int

    var dotsClickable: Boolean = true
    var dotsColor: Int = 0
        set(value) {
            field = value
            refreshDotsColors()
        }
    protected var dotsSize = dpToPxF(10F)
    protected var dotsCornerRadius = dpToPxF(6F)
    protected var dotsSpacing = dpToPxF(4F)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LPStepperCarousel, 0 ,0).apply {
            try {
                dotColor = getInt(R.styleable.LPStepperCarousel_stepperColor, 0)
                dotSize = getInt(R.styleable.LPStepperCarousel_stepperSize, 0)
                dotCornerRadius = getInt(R.styleable.LPStepperCarousel_stepperSize, 0)
                dotSpacing = getInt(R.styleable.LPStepperCarousel_stepperSize, 0)
            } finally {
                recycle()
            }
        }

        dotsColor = when (dotColor) {
            0 -> ContextCompat.getColor(context, R.color.pensive5)
            1 -> ContextCompat.getColor(context, R.color.white1)
            else -> ContextCompat.getColor(context, R.color.pensive5)
        }
        dotsSize = when (dotSize) {
            0 -> dpToPxF(10F)
            1 -> dpToPxF(5F)
            else -> dpToPxF(10F)
        }
        dotsCornerRadius = when (dotCornerRadius) {
            0 -> dpToPxF(6F)
            1 -> dpToPxF(2F)
            else -> dpToPxF(6F)
        }
        dotsSpacing = when (dotSpacing) {
            0 -> dpToPxF(4F)
            1 -> dpToPxF(2F)
            else -> dpToPxF(4F)
        }
    }

    var pager: Pager? = null

    interface Pager {
        val isNotEmpty: Boolean
        val currentItem: Int
        val isEmpty: Boolean
        val count: Int
        fun setCurrentItem(item: Int, smoothScroll: Boolean)
        fun removeOnPageChangeListener()
        fun addOnPageChangeListener(onPageChangeListenerHelper: OnPageChangeListenerHelper)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        refreshDots()
    }

    private fun refreshDotsCount() {
        if (dots.size < pager!!.count) {
            addDots(pager!!.count - dots.size)
        } else if (dots.size > pager!!.count) {
            removeDots(dots.size - pager!!.count)
        }
    }

    protected fun refreshDotsColors() {
        for (i in dots.indices) {
            refreshDotColor(i)
        }
    }

    protected fun dpToPxF(dp: Float): Float {
        return context.resources.displayMetrics.density * dp
    }

    protected fun addDots(count: Int) {
        for (i in 0 until count) {
            addDot(i)
        }
    }

    private fun removeDots(count: Int) {
        for (i in 0 until count) {
            removeDot(i)
        }
    }

    fun refreshDots() {
        if (pager == null) {
            return
        }
        post {
            // Check if we need to refresh the dots count
            refreshDotsCount()
            refreshDotsColors()
            refreshDotsSize()
            refreshOnPageChangedListener()
        }
    }

    private fun refreshOnPageChangedListener() {
        if (pager!!.isNotEmpty) {
            pager!!.removeOnPageChangeListener()
            val onPageChangeListenerHelper = buildOnPageChangedListener()
            pager!!.addOnPageChangeListener(onPageChangeListenerHelper)
            onPageChangeListenerHelper.onPageScrolled(pager!!.currentItem, 0f)
        }
    }

    private fun refreshDotsSize() {
        dots.forEach {
            it.setWidth(dotsSize.toInt())
        }
    }

    abstract fun refreshDotColor(index: Int)
    abstract fun addDot(index: Int)
    abstract fun removeDot(index: Int)
    abstract fun buildOnPageChangedListener(): OnPageChangeListenerHelper

    //Public Method

    @Deprecated("Use setDotsColors() instead")
    fun setPointsColor(color: Int) {
        dotsColor = color
        refreshDotsColors()
    }

    fun setViewPager(viewPager: ViewPager) {
        if (viewPager.adapter == null) {
            throw IllegalStateException(
                "You have to set an adapter to the view pager before " +
                        "initializing the dots indicator !"
            )
        }

        viewPager.adapter!!.registerDataSetObserver(object : DataSetObserver() {
            override fun onChanged() {
                super.onChanged()
                refreshDots()
            }
        })

        pager = object : Pager {
            var onPageChangeListener: ViewPager.OnPageChangeListener? = null

            override val isNotEmpty: Boolean
                get() = viewPager.isNotEmpty
            override val currentItem: Int
                get() = viewPager.currentItem
            override val isEmpty: Boolean
                get() = viewPager.isEmpty
            override val count: Int
                get() = viewPager.adapter?.count ?: 0

            override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
                viewPager.setCurrentItem(item, smoothScroll)
            }

            override fun removeOnPageChangeListener() {
                onPageChangeListener?.let { viewPager.removeOnPageChangeListener(it) }
            }

            override fun addOnPageChangeListener(
                onPageChangeListenerHelper:
                OnPageChangeListenerHelper
            ) {
                onPageChangeListener = object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int, positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        onPageChangeListenerHelper.onPageScrolled(position, positionOffset)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }

                    override fun onPageSelected(position: Int) {
                    }
                }
                viewPager.addOnPageChangeListener(onPageChangeListener!!)
            }
        }

        refreshDots()
    }

    fun setViewPager2(viewPager2: ViewPager2) {
        if (viewPager2.adapter == null) {
            throw IllegalStateException(
                "You have to set an adapter to the view pager before " +
                        "initializing the dots indicator !"
            )
        }


        viewPager2.adapter!!.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                refreshDots()
            }
        })

        pager = object : Pager {
            var onPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

            override val isNotEmpty: Boolean
                get() = viewPager2.isNotEmpty
            override val currentItem: Int
                get() = viewPager2.currentItem
            override val isEmpty: Boolean
                get() = viewPager2.isEmpty
            override val count: Int
                get() = viewPager2.adapter?.itemCount ?: 0

            override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
                viewPager2.setCurrentItem(item, smoothScroll)
            }

            override fun removeOnPageChangeListener() {
                onPageChangeCallback?.let { viewPager2.unregisterOnPageChangeCallback(it) }
            }

            override fun addOnPageChangeListener(
                onPageChangeListenerHelper: OnPageChangeListenerHelper
            ) {
                onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int, positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        onPageChangeListenerHelper.onPageScrolled(position, positionOffset)
                    }
                }
                viewPager2.registerOnPageChangeCallback(onPageChangeCallback!!)
            }
        }

        refreshDots()
    }


    fun View.setWidth(width: Int) {
        layoutParams.apply {
            this.width = width
            requestLayout()
        }
    }

    fun <T> ArrayList<T>.isInBounds(index: Int) = index in 0 until size

    protected val ViewPager.isNotEmpty: Boolean get() = (adapter?.count ?: 0 )> 0
    protected val ViewPager2.isNotEmpty: Boolean get() = (adapter?.itemCount ?: 0) > 0


    protected val ViewPager?.isEmpty: Boolean
        get() = this != null && this.adapter != null &&
                adapter!!.count == 0

    protected val ViewPager2?.isEmpty: Boolean
        get() = this != null && this.adapter != null &&
                adapter!!.itemCount == 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        layoutDirection = View.LAYOUT_DIRECTION_LTR
        rotation = 180f
        requestLayout()
    }
}
