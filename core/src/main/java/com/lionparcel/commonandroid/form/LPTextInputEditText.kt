package com.lionparcel.commonandroid.form

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import com.google.android.material.textfield.TextInputEditText
import com.lionparcel.commonandroid.R

class LPTextInputEditText: TextInputEditText {

    companion object {
        private const val MINIMUM_HEIGHT_PX = 55
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
        : super(ContextThemeWrapper(context, R.style.LPTextInputEditText), attrs, defStyleAttr)
}
