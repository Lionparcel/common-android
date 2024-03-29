package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroid.R

class LPTextInputLayout: TextInputLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
        : this(context, attrs, R.attr.LPTextInputLayoutStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
        : super(ContextThemeWrapper(context, R.style.LPTextInputLayoutTheme), attrs, defStyleAttr)

    init {
        // set theme (somehow we should do this despite already having set the theme in the constructor)
        context.setTheme(R.style.LPTextInputLayoutTheme)

        // centering prefix & suffix text
        prefixTextView.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        prefixTextView.gravity = Gravity.CENTER
        suffixTextView.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        suffixTextView.gravity = Gravity.CENTER

        // force prefix & suffix to be visible at all time
        prefixTextView.visibility = View.VISIBLE
        suffixTextView.visibility = View.VISIBLE
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            prefixTextView.visibility = View.VISIBLE
            suffixTextView.visibility = View.VISIBLE
        }
    }
}
