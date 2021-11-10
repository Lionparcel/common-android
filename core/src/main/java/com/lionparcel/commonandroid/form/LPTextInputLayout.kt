package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroid.R

class LPTextInputLayout: TextInputLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
        : this(context, attrs, R.attr.LPTextInputLayoutStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
        : super(ContextThemeWrapper(context, R.style.LPTextInputLayoutTheme), attrs, defStyleAttr)

    init {
        val prefixView = this.findViewById<AppCompatTextView>(com.google.android.material.R.id.textinput_prefix_text)
        prefixView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        prefixView.gravity = Gravity.CENTER
    }
}
