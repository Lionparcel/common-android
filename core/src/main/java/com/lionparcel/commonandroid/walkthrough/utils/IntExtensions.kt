package com.lionparcel.commonandroid.walkthrough.utils

import android.content.res.Resources

internal fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density + 0.5F).toInt()