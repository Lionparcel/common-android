package com.lionparcel.commonandroid.stepper

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.stepper.utils.BaseCarouselIndicator
import com.lionparcel.commonandroid.stepper.utils.OnPageChangeListenerHelper
import com.lionparcel.commonandroid.stepper.utils.StepperGradientDrawable

class LPStepperCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defAttributeStyle: Int = 0
) : BaseCarouselIndicator(context, attrs, defAttributeStyle) {

    private var linearLayout: LinearLayout? = null
    private var stepperWidthFactor: Float = 3f
    private var progressMode: Boolean = false
    private var stepperSize : Int
    private var stepperColor : Int

    var selectedStepperColor: Int = 0
        set(value) {
            field = value
            refreshDotsColors()
        }

    private val argbEvaluator = ArgbEvaluator()

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LPStepperCarousel, 0, 0).apply {
            try {
                stepperSize = getInt(R.styleable.LPStepperCarousel_stepperSize, 0)
                stepperColor = getInt(R.styleable.LPStepperCarousel_stepperColor, 0)
                progressMode = getBoolean(R.styleable.LPStepperCarousel_progressMode, false)
            } finally {
                recycle()
            }
        }


        linearLayout = LinearLayout(context)
        linearLayout!!.orientation = LinearLayout.HORIZONTAL
        addView(
            linearLayout,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        selectedStepperColor = when (stepperColor) {
            0 -> ContextCompat.getColor(context, R.color.interpack6)
            1 -> ContextCompat.getColor(context, R.color.white)
            else -> ContextCompat.getColor(context, R.color.interpack6)
        }

        if (isInEditMode) {
            addDots(5)
            refreshDots()
        }
    }

    override fun refreshDotColor(index: Int) {
        val elevationItem = dots[index]
        val background = elevationItem.background as StepperGradientDrawable

        if (index == pager!!.currentItem || progressMode && index < pager!!.currentItem) {
            background.setColor(selectedStepperColor)
        } else {
            background.setColor(dotsColor)
        }

        elevationItem.background = background
        elevationItem.invalidate()
    }

    override fun addDot(index: Int) {
        val stepper =
            LayoutInflater.from(context).inflate(R.layout.lp_layout_stepper_carousel, this, false)
        val imageView = stepper.findViewById<ImageView>(R.id.iv_stepper_carousel_dot)
        val params = imageView.layoutParams as LayoutParams

        stepper.layoutDirection = View.LAYOUT_DIRECTION_LTR
        params.width = dotsSize.toInt()
        params.height = when (stepperSize) {
            0 -> dotsSize.toInt() - 2
            1 -> dotsSize.toInt() - 1
            else -> 0
        }
        params.setMargins(dotsSpacing.toInt(), 0, dotsSpacing.toInt(), 0 )
        val background = StepperGradientDrawable()
        background.cornerRadius = dotsCornerRadius
        if (isInEditMode) {
            background.setColor(if ( 0 == index) selectedStepperColor else dotsColor)
        } else {
            background.setColor(if (pager!!.currentItem == index) selectedStepperColor else dotsColor)
        }
        imageView.background = background

        stepper.setOnClickListener {
            if (dotsClickable && index < pager?.count ?: 0) {
                pager!!.setCurrentItem(index, true)
            }
        }

        dots.add(imageView)
        linearLayout!!.addView(stepper)
    }

    override fun removeDot(index: Int) {
        linearLayout!!.removeViewAt(childCount - 1)
        dots.removeAt(dots.size - 1)
    }

    override fun buildOnPageChangedListener(): OnPageChangeListenerHelper {
        return object : OnPageChangeListenerHelper() {
            override val pageCount: Int
                get() = dots.size

            override fun onPageScrolled(
                selectedPosition: Int,
                nextPosition: Int,
                positionOffset: Float
            ) {
                val selectedDot = dots[selectedPosition]

                val selectedDotWidth = (dotsSize + dotsSize * (stepperWidthFactor - 1) * (1 - positionOffset)).toInt()
                selectedDot.setWidth(selectedDotWidth)

                if (dots.isInBounds(nextPosition)) {
                    val nextDot = dots[nextPosition]

                    val nextDotWidth = (dotsSize + dotsSize * (stepperWidthFactor - 1) * positionOffset).toInt()
                    nextDot.setWidth(nextDotWidth)

                    val selectedDotBackground = selectedDot.background as StepperGradientDrawable
                    val nextDotBackground = nextDot.background as StepperGradientDrawable

                    if (selectedStepperColor != dotsColor) {
                        val selectedColor = argbEvaluator.evaluate(positionOffset, selectedStepperColor,
                            dotsColor) as Int
                        val nextColor = argbEvaluator.evaluate(positionOffset, dotsColor,
                            selectedStepperColor) as Int

                        nextDotBackground.setColor(nextColor)

                        if (progressMode && selectedPosition <= pager!!.currentItem) {
                            selectedDotBackground.setColor(selectedStepperColor)
                        } else {
                            selectedDotBackground.setColor(selectedColor)
                        }
                    }
                }


                invalidate()
            }

            override fun resetPosition(position: Int) {
                dots[position].setWidth(dotsSize.toInt())
                refreshDotColor(position)
            }

        }
    }

}