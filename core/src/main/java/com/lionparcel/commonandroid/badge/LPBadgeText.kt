package com.lionparcel.commonandroid.badge

import android.content.Context
import android.util.AttributeSet
import com.lionparcel.commonandroid.badge.utils.BaseBadgeComponent

class LPBadgeText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : BaseBadgeComponent(context, attrs, defStyleAttrs) {

    override val type: Type
        get() = Type.TEXT

    fun setTitle(title: String) {
        setText(title)
    }
}