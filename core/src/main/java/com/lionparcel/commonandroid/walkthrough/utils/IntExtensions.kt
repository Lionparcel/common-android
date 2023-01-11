package com.lionparcel.commonandroid.walkthrough.utils

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

internal fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density + 0.5F).toInt()

val Int.dp: Int
    inline get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()