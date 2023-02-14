package com.lionparcel.commonandroid.selectioncontrol

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPToggle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SwitchCompat(context, attrs) {

    init {
        initLPToggle()
    }

    private fun initLPToggle() {
        this.thumbDrawable = ContextCompat.getDrawable(context, R.drawable.bg_switch_thumb)
        this.trackDrawable = ContextCompat.getDrawable(context, R.drawable.bg_switch_track)
    }

    fun disableToggle() {
        this.alpha = 0.5F
        this.isEnabled = false
    }
}