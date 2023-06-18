package com.lionparcel.commonandroid.dropdown.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner

class LPCustomSpinner @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatSpinner(context, attrs) {

    private var spinnerEventsListener: OnSpinnerEventsListener? = null
    private var openInitiated: Boolean = false

    override fun performClick(): Boolean {
        openInitiated = true
        if (spinnerEventsListener != null) {
            spinnerEventsListener!!.onSpinnerOpened(this)
        }
        return super.performClick()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (this.hasBeenOpened() && this.hasFocus()) {
            performClosedEvent()
        }
    }

    fun setSpinnerEventsListener(onSpinnerEventsListener: OnSpinnerEventsListener) {
        spinnerEventsListener = onSpinnerEventsListener
    }

    fun performClosedEvent() {
        openInitiated = false
        if (spinnerEventsListener != null) {
            spinnerEventsListener!!.onSpinnerClosed(this)
        }
    }

    fun hasBeenOpened(): Boolean {
        return openInitiated
    }



}