package com.lionparcel.commonandroid.button

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import com.lionparcel.commonandroid.R

class LPButton : AppCompatButton {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
            : this(context, attrs, R.attr.LPButtonStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
            : super(ContextThemeWrapper(context, R.style.LPButtonTheme), attrs, defStyleAttr)
}