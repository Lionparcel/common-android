package com.lionparcel.commonandroid.badge

import android.content.Context
import android.util.AttributeSet
import com.lionparcel.commonandroid.badge.utils.BaseBadgeComponent

class LPBadgeNumber @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) :
    BaseBadgeComponent(context, attrs, defStyleAttrs) {

    override val type: Type
        get() = Type.NUMBER
}