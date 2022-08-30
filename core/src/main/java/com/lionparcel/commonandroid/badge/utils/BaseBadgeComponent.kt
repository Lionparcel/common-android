package com.lionparcel.commonandroid.badge.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class BaseBadgeComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    enum class Type(
        val styleableId: IntArray
    ) {
//        NUMBER(
//
//        ),
//        DOT(
//
//        ),
//        TEXT(
//
//        )
    }

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, type.styleableId).apply{
                try {

                } finally {
                    recycle()
                }
            }
        }
    }

    abstract val type: Type
}