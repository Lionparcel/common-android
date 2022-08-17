package com.lionparcel.commonandroid.tooltip.utils

enum class TooltipCenterAlign {
    BOTTOM,
    TOP,
    START,
    END;

    internal companion object {

        internal fun TooltipCenterAlign.getRTLSupportAlign(isRtlLayout: Boolean): TooltipCenterAlign {
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