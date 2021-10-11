package com.lionparcel.commonandroid.modal

import android.content.Context

// It Use For now in Dialog Max Size is 90% Screen Device
fun setMaxDefaultHeight(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val height = displayMetrics.heightPixels
    return (height * 0.90).toInt()
}

// It Use For now in Dialog Max Size on recycleview 70% Screen Device
fun setMaxDefaultHeightContent(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val height = displayMetrics.heightPixels
    return (height * 0.70).toInt()
}
