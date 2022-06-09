package com.lionparcel.commonandroid.form

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.lionparcel.commonandroid.R

class LPAutoCompleteTextView : AppCompatAutoCompleteTextView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null)
    : this(context, attrs, R.attr.autoCompleteTextViewStyle)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
    : super(ContextThemeWrapper(context, R.style.LPTextInputEditTextTheme), attrs, defStyleAttr)


    fun setClearIcon(startDrawable: Int? = null, isEnabled : Boolean) {
        val clearIcon = if (this.text.isNotEmpty() && isEnabled) {
            R.drawable.ics_f_close_circle
        } else 0
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(
            startDrawable ?: 0, 0, clearIcon, 0
        )
    }

    fun handleOnClearIconClick(){
        this.setOnTouchListener(object : OnTouchListener{
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                if (handleRightDrawableTouch(view as TextView, motionEvent!!)) {
                    this@LPAutoCompleteTextView.text.clear()
                    this@LPAutoCompleteTextView.requestFocus()
                }
                return view.onTouchEvent(motionEvent)
            }

        })
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    private fun handleRightDrawableTouch(textView: TextView, motionEvent: MotionEvent): Boolean {
        val rightDrawable = textView.compoundDrawables[2]
        return rightDrawable != null && motionEvent.rawX >=
                (textView.right - rightDrawable.bounds.width() - textView.paddingRight)
    }

}