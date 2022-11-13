package com.lionparcel.commonandroid.datepicker.utils

import android.graphics.drawable.GradientDrawable
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun GradientDrawable.setCornerRadius(
    topLeft: Float = 0F,
    topRight: Float = 0F,
    bottomLeft: Float = 0F,
    bottomRight: Float = 0F
) {
    cornerRadii = arrayOf(
        topLeft, topLeft,
        topRight, topRight,
        bottomRight, bottomRight,
        bottomLeft, bottomLeft
    ).toFloatArray()
}

fun LocalDate.toDate(): Date = Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())

fun Date.toLocaleDate(): LocalDate = toInstant().atZone(ZoneId.systemDefault()).toLocalDate()