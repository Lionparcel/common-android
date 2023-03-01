package com.lionparcel.commonandroid.stepper

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.stepper.utils.StepperDotPosition
import com.lionparcel.commonandroid.stepper.utils.StepperDotState

class LPStepperDot : ConstraintLayout{

    private var stepperOrientation : Int
    private var stepperDirection : Int

    private val clStepperDot : ConstraintLayout
    private val ivStepperDotIndicator : ImageView
    private val vwStepperDotLineVer1 : View
    private val vwStepperDotLineVer2 : View
    private val vwStepperDotLineHor1 : View
    private val vwStepperDotLineHor2 : View

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_stepper_dot, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPStepperDot,
            0,
            0
        ).apply {
            try {
                stepperOrientation = getInt(R.styleable.LPStepperDot_stepperOrientation, 0)
                stepperDirection = getInt(R.styleable.LPStepperDot_stepperDirection, 0)
            } finally {
                recycle()
            }
        }

        clStepperDot = findViewById(R.id.cl_stepper_dot)
        ivStepperDotIndicator = findViewById(R.id.iv_stepper_dot_indicator)
        vwStepperDotLineVer1 = findViewById(R.id.vw_stepper_dot_line_ver_1)
        vwStepperDotLineVer2 = findViewById(R.id.vw_stepper_dot_line_ver_2)
        vwStepperDotLineHor1 = findViewById(R.id.vw_stepper_dot_line_hor_1)
        vwStepperDotLineHor2 = findViewById(R.id.vw_stepper_dot_line_hor_2)

        setStepperOrientationAndDirection()
    }
    private fun setStepperOrientationAndDirection(position: StepperDotPosition? = null){
        when (stepperOrientation) {
            0 -> when (position) {
                StepperDotPosition.NORMAL -> when (stepperDirection) {
                    0 -> vwStepperDotLineVer2.isVisible = true
                    1 -> vwStepperDotLineVer1.isVisible = true
                }
                StepperDotPosition.LAST -> when (stepperDirection) {
                    0 -> vwStepperDotLineVer2.isVisible = false
                    1 -> vwStepperDotLineVer1.isVisible = false
                }
            }
            1 -> when (position) {
                StepperDotPosition.NORMAL -> when (stepperDirection) {
                    0 -> vwStepperDotLineHor2.isVisible = true
                    1 -> vwStepperDotLineHor1.isVisible = true
                }
                StepperDotPosition.LAST -> when (stepperDirection) {
                    0 -> vwStepperDotLineHor2.isVisible = false
                    1 -> vwStepperDotLineHor1.isVisible = false
                }
            }
        }
    }

    fun stepperPosition(position: StepperDotPosition) {
        setStepperOrientationAndDirection(position)
    }

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun stepperState(state : StepperDotState) {
        when (state) {
            StepperDotState.DEFAULT -> {
                ImageViewCompat.setImageTintList(ivStepperDotIndicator, resources.getColorStateList(R.color.shades2))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer1, resources.getColorStateList(R.color.shades2))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer2, resources.getColorStateList(R.color.shades2))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor1, resources.getColorStateList(R.color.shades2))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor2, resources.getColorStateList(R.color.shades2))
            }
            StepperDotState.PRIMARY -> {
                ImageViewCompat.setImageTintList(ivStepperDotIndicator, resources.getColorStateList(R.color.interpack7))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer1, resources.getColorStateList(R.color.interpack7))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer2, resources.getColorStateList(R.color.interpack7))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor1, resources.getColorStateList(R.color.interpack7))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor2, resources.getColorStateList(R.color.interpack7))
            }
            StepperDotState.FAILED -> {
                ImageViewCompat.setImageTintList(ivStepperDotIndicator, resources.getColorStateList(R.color.interpack5))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer1, resources.getColorStateList(R.color.interpack5))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer2, resources.getColorStateList(R.color.interpack5))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor1, resources.getColorStateList(R.color.interpack5))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor2, resources.getColorStateList(R.color.interpack5))
            }
            StepperDotState.SUCCESS -> {
                ImageViewCompat.setImageTintList(ivStepperDotIndicator, resources.getColorStateList(R.color.green6))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer1, resources.getColorStateList(R.color.green6))
                ViewCompat.setBackgroundTintList(vwStepperDotLineVer2, resources.getColorStateList(R.color.green6))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor1, resources.getColorStateList(R.color.green6))
                ViewCompat.setBackgroundTintList(vwStepperDotLineHor2, resources.getColorStateList(R.color.green6))
            }
        }
    }
}
