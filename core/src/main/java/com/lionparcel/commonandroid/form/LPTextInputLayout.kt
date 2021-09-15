package com.lionparcel.commonandroid.form

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import com.lionparcel.commonandroid.R

class LPTextInputLayout: TextInputLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
        : this(context, attrs, R.attr.LPTextInputLayoutStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
        : super(ContextThemeWrapper(context, R.style.LPTextInputLayoutTheme), attrs, defStyleAttr)
}