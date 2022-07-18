package com.lionparcel.commonandroid.stepper.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.lionparcel.commonandroid.R

class BaseCarouselIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val DEFAULT_COLOR = Color.LTGRAY
    }

    @JvmField
    protected val dots = ArrayList<ImageView>()

    var dotsClickable : Boolean = true
    var dotsColor : Int
}