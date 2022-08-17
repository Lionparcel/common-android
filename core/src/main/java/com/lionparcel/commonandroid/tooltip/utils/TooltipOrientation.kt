package com.lionparcel.commonandroid.tooltip.utils

enum class TooltipOrientation {
    BOTTOM,
    TOP,
    START,
    END;

    internal companion object {
       internal fun TooltipOrientation.getRTLSupportOrientation(isRtlLayout: Boolean): TooltipOrientation {
           return if (!isRtlLayout) {
               this
           } else {
               when (this) {
                   START -> END
                   END -> START
                   else -> this
               }
           }
       }
    }
}