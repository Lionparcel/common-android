package com.lionparcel.commonandroid.stepper.utils

import android.widget.ImageView
import com.lionparcel.commonandroid.R

enum class StepperDotState(val value : String) {
    PRIMARY("PRIMARY"),
    DEFAULT("DEFAULT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS")
}

enum class StepperDotPosition(val value: String) {
    NORMAL("NORMAL"),
    LAST("LAST")
}

enum class StepperNumberState(val value: String) {
    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE"),
    FINISHED("FINISHED"),
    FAILED("FAILED")
}

enum class StepperIconState(val value: String) {
    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    WARNING("WARNING")
}
