package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import com.google.android.material.textfield.TextInputEditText
import com.lionparcel.commonandroid.R

class LPTextInputEditText: TextInputEditText {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
        : this(context, attrs, R.attr.LPTextInputEditTextStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
        : super(ContextThemeWrapper(context, R.style.LPTextInputEditTextTheme), attrs, defStyleAttr)
}
