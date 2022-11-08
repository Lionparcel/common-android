package com.lionparcel.commonandroid.selectioncontrol

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatCheckBox(context, attrs, defStyleAttr) {

    private var size: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPCheckBox,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPCheckBox_checkBoxSize, 0)
            } finally {
                recycle()
            }
        }
        setCheckBoxSize()
    }

    private fun setCheckBoxSize() {
        when (size) {
            0 -> this.buttonDrawable =
                ContextCompat.getDrawable(context, R.drawable.ic_selector_checkbox_red_20)
            1 -> this.buttonDrawable =
                ContextCompat.getDrawable(context, R.drawable.ic_selector_checkbox_red_16)
        }
    }

    fun disableCheckBox() {
        this.alpha = 0.5F
        this.isEnabled = false
    }
}