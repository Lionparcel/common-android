package com.lionparcel.commonandroid.dropdown.utils

import android.widget.Spinner

interface OnSpinnerEventsListener {

    fun onSpinnerOpened(spinner: Spinner)

    fun onSpinnerClosed(spinner: Spinner)

}