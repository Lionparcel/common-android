package com.lionparcel.commonandroid.form.utils

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class TypeFaceSpanCompat(text : String, private val newTypeface : Typeface) : TypefaceSpan(text) {

    override fun updateDrawState(ds: TextPaint) {
         applyCustomTypeFace(ds, newTypeface)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newTypeface)
    }

    private fun applyCustomTypeFace(paint: Paint, typeFace : Typeface) {
        val originalStyle = paint.typeface?.style ?: 0

        val fake = originalStyle and typeFace.style.inv()
        if (fake and Typeface.BOLD != 0) {
            paint.isFakeBoldText = true
        }

        if (fake and Typeface.ITALIC !=0) {
            paint.textSkewX = -0.25f
        }

        paint.typeface = typeFace
    }
}