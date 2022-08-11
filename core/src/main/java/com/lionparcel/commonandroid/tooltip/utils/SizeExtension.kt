package com.lionparcel.commonandroid.tooltip.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
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

internal val Context.isFinishing: Boolean
    @JvmSynthetic inline get() = this is Activity && this.isFinishing