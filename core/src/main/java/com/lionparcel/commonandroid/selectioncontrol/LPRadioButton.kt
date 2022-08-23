package com.lionparcel.commonandroid.selectioncontrol

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    private var size: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPRadioButton,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPRadioButton_radioButtonSize, 0)
            } finally {
                recycle()
            }
        }
        setRadioButtonSize()
    }

    private fun setRadioButtonSize() {
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> this.background =
                ContextCompat.getDrawable(context, R.drawable.ic_selector_radio_button_red_20)
            1 -> {
                this.background =
                    ContextCompat.getDrawable(context, R.drawable.ic_selector_radio_button_red_16)
                this.maxHeight = (16 * scale).toInt()
            }
        }
    }

    fun disableRadioButton() {
        this.alpha = 0.5F
        this.isEnabled = false
    }

}