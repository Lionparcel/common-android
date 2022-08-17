package com.lionparcel.commonandroid.tooltip.utils

import com.lionparcel.commonandroid.tooltip.LPTooltip

class AutoDismissRunnable(val lpTooltip: LPTooltip) : Runnable{

    override fun run() {
        lpTooltip.dismiss()
    }
}