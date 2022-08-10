package com.lionparcel.commonandroid.stepper.utils

import android.graphics.drawable.GradientDrawable

class StepperGradientDrawable : GradientDrawable() {
    var currentColor: Int = 0
        private set

    override fun setColor(argb: Int) {
        super.setColor(argb)
        currentColor = argb
    }
}
