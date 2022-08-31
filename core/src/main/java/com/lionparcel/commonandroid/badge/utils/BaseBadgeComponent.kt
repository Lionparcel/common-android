package com.lionparcel.commonandroid.badge.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.lionparcel.commonandroid.R

abstract class BaseBadgeComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs) {

    enum class Type(
        val styleableId: IntArray
    ) {
        NUMBER(
            R.styleable.LPBadgeNumber
        ),
//        TEXT(
//
//        )
    }

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, type.styleableId).apply {
                try {

                } finally {
                    recycle()
                }
            }
        }
    }

    abstract val type: Type
}