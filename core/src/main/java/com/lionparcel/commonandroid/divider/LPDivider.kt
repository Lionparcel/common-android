package com.lionparcel.commonandroid.divider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R

class LPDivider : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_divider_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPDivider,
            0,
            0
        ).apply {
            try {

            } finally {
                recycle()
            }
        }
    }
}