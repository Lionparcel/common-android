package com.lionparcel.commonandroid.tooltip.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.TypedValue
import android.view.View
import kotlin.math.roundToInt

internal val Int.dp: Int
    @JvmSynthetic inline get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()

internal val Float.dp: Float
    @JvmSynthetic inline get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

internal val displaySize: Point
    @JvmSynthetic inline get() = Point(
        Resources.getSystem().displayMetrics.widthPixels,
        Resources.getSystem().displayMetrics.heightPixels
    )

internal val Context.isFinishing: Boolean
    @JvmSynthetic inline get() = this is Activity && this.isFinishing

@JvmSynthetic
internal fun View.getViewPointOnScreen(): Point {
    val location: IntArray = intArrayOf(0, 0)
    getLocationOnScreen(location)
    return Point(location[0], location[1])
}

@JvmSynthetic
internal fun View.visible(visible: Boolean){
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@JvmSynthetic
internal fun Int.unaryMinus(predicate: Boolean): Int {
    return if (predicate) {
        unaryMinus()
    } else {
        this
    }
}

@get:JvmSynthetic
internal const val LTR: Int = 1