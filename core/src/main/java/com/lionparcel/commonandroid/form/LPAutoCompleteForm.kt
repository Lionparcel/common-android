package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.lionparcel.commonandroid.R

class LPAutoCompleteForm : AppCompatAutoCompleteTextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
            : this(context, attrs, R.attr.autoCompleteTextViewStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
            : super(ContextThemeWrapper(context, R.style.LPTextInputEditTextTheme), attrs, defStyleAttr)
}