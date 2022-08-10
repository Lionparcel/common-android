package com.lionparcel.commonandroid.stepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.stepper.utils.StepperNumberState

class LPStepperNumber : ConstraintLayout {

    private var stepperTextVisibility: Int
    private var stepperNumberCount: Int
    private var firstTitle: String
    private var secondTitle: String
    private var thirdTitle: String
    private var firstSubtitle: String
    private var secondSubtitle: String
    private var thirdSubtitle: String

    private val clStepperNumber: ConstraintLayout
    private val llStepperNumberFirstIcon: LinearLayout
    private val llStepperNumberSecondIcon: LinearLayout
    private val llStepperNumberThirdIcon: LinearLayout
    private val llStepperNumberFirstText: LinearLayout
    private val llStepperNumberSecondText: LinearLayout
    private val llStepperNumberThirdText: LinearLayout
    private val vwStepperNumberLine1: View
    private val vwStepperNumberLine2: View
    private val tvStepperNumberFirst: TextView
    private val tvStepperNumberSecond: TextView
    private val tvStepperNumberThird: TextView
    private val ivStepperNumberFirst: ImageView
    private val ivStepperNumberSecond: ImageView
    private val ivStepperNumberThird: ImageView
    private val tvStepperNumberTitleFirst: TextView
    private val tvStepperNumberTitleSecond: TextView
    private val tvStepperNumberTitleThird: TextView
    private val tvStepperNumberSubtitleFirst: TextView
    private val tvStepperNumberSubtitleSecond: TextView
    private val tvStepperNumberSubtitleThird: TextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_stepper_number, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPStepperNumber,
            0,
            0
        ).apply {
            try {
                stepperTextVisibility = getInt(R.styleable.LPStepperNumber_stepperText, 0)
                stepperNumberCount = getInt(R.styleable.LPStepperNumber_stepperNumberCount, 0)
                firstTitle = getString(R.styleable.LPStepperNumber_firstTitle)
                    ?: resources.getString(R.string.stepper_number_title_one)
                secondTitle = getString(R.styleable.LPStepperNumber_secondTitle)
                    ?: resources.getString(R.string.stepper_number_title_two)
                thirdTitle = getString(R.styleable.LPStepperNumber_thirdTitle)
                    ?: resources.getString(R.string.stepper_number_title_three)
                firstSubtitle = getString(R.styleable.LPStepperNumber_firstSubtitle)
                    ?: resources.getString(R.string.stepper_number_subtitle_one)
                secondSubtitle = getString(R.styleable.LPStepperNumber_secondSubtitle)
                    ?: resources.getString(R.string.stepper_number_subtitle_two)
                thirdSubtitle = getString(R.styleable.LPStepperNumber_thirdSubtitle)
                    ?: resources.getString(R.string.stepper_number_subtitle_three)
            } finally {
                recycle()
            }

            clStepperNumber = findViewById(R.id.cl_stepper_number)
            llStepperNumberFirstIcon = findViewById(R.id.ll_stepper_number_first_icon)
            llStepperNumberSecondIcon = findViewById(R.id.ll_stepper_number_second_icon)
            llStepperNumberThirdIcon = findViewById(R.id.ll_stepper_number_third_icon)
            llStepperNumberFirstText = findViewById(R.id.ll_stepper_number_first_text)
            llStepperNumberSecondText = findViewById(R.id.ll_stepper_number_second_text)
            llStepperNumberThirdText = findViewById(R.id.ll_stepper_number_third_text)
            vwStepperNumberLine1 = findViewById(R.id.vw_stepper_number_line_1)
            vwStepperNumberLine2 = findViewById(R.id.vw_stepper_number_line_2)
            tvStepperNumberFirst = findViewById(R.id.tv_stepper_number_first)
            tvStepperNumberSecond = findViewById(R.id.tv_stepper_number_second)
            tvStepperNumberThird = findViewById(R.id.tv_stepper_number_third)
            ivStepperNumberFirst = findViewById(R.id.iv_stepper_number_first)
            ivStepperNumberSecond = findViewById(R.id.iv_stepper_number_second)
            ivStepperNumberThird = findViewById(R.id.iv_stepper_number_third)
            tvStepperNumberTitleFirst = findViewById(R.id.tv_stepper_number_title_first)
            tvStepperNumberTitleSecond = findViewById(R.id.tv_stepper_number_title_second)
            tvStepperNumberTitleThird = findViewById(R.id.tv_stepper_number_title_third)
            tvStepperNumberSubtitleFirst = findViewById(R.id.tv_stepper_number_subtitle_first)
            tvStepperNumberSubtitleSecond = findViewById(R.id.tv_stepper_number_subtitle_second)
            tvStepperNumberSubtitleThird = findViewById(R.id.tv_stepper_number_subtitle_third)

            setTextVisibilityAndNumberCount()
            setTitleAndSubtitle()
        }
    }

    private fun setTextVisibilityAndNumberCount() {
        val set = ConstraintSet()
        when (stepperNumberCount) {
            0 -> when (stepperTextVisibility) {
                0 -> {
                    llStepperNumberFirstIcon.isVisible = true
                    llStepperNumberSecondIcon.isVisible = true
                    llStepperNumberThirdIcon.isVisible = true
                    llStepperNumberFirstText.isVisible = true
                    llStepperNumberSecondText.isVisible = true
                    llStepperNumberThirdText.isVisible = true
                }
                1 -> {
                    llStepperNumberFirstIcon.isVisible = true
                    llStepperNumberSecondIcon.isVisible = true
                    llStepperNumberThirdIcon.isVisible = true
                    llStepperNumberFirstText.isVisible = false
                    llStepperNumberSecondText.isVisible = false
                    llStepperNumberThirdText.isVisible = false
                }
            }
            1 -> {
                when (stepperTextVisibility) {
                    0 -> {
                        llStepperNumberFirstIcon.isVisible = true
                        llStepperNumberSecondIcon.isVisible = true
                        llStepperNumberThirdIcon.isVisible = false
                        llStepperNumberFirstText.isVisible = true
                        llStepperNumberSecondText.isVisible = true
                        llStepperNumberThirdText.isVisible = false
                        vwStepperNumberLine2.isVisible = false
                    }
                    1 -> {
                        llStepperNumberFirstIcon.isVisible = true
                        llStepperNumberSecondIcon.isVisible = true
                        llStepperNumberThirdIcon.isVisible = false
                        llStepperNumberFirstText.isVisible = false
                        llStepperNumberSecondText.isVisible = false
                        llStepperNumberThirdText.isVisible = false
                        vwStepperNumberLine2.isVisible = false
                    }
                }
                set.clone(clStepperNumber)
                set.clear(R.id.ll_stepper_number_second_icon, ConstraintSet.END)
                set.connect(
                    R.id.ll_stepper_number_second_icon,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END,
                    resources.getDimension(R.dimen.dp_36).toInt()
                )
                set.applyTo(clStepperNumber)
                invalidate()
                requestLayout()
            }
        }
    }

    fun setTitleAndSubtitle(
        firstTitle: String? = null,
        secondTitle: String? = null,
        thirdTitle: String? = null,
        firstSubtitle: String? = null,
        secondSubtitle: String? = null,
        thirdSubtitle: String? = null,
    ) {
        tvStepperNumberTitleFirst.text = firstTitle?: this.firstTitle
        tvStepperNumberTitleSecond.text = secondTitle?: this.secondTitle
        tvStepperNumberTitleThird.text = thirdTitle?: this.thirdTitle
        tvStepperNumberSubtitleFirst.text = firstSubtitle?: this.firstSubtitle
        tvStepperNumberSubtitleSecond.text = secondSubtitle?: this.secondSubtitle
        tvStepperNumberSubtitleThird.text = thirdSubtitle?: this.thirdSubtitle
    }

    fun firstStepper(state: StepperNumberState) {
        when (state) {
            StepperNumberState.INACTIVE -> {
                ivStepperNumberFirst.isVisible = false
                tvStepperNumberFirst.isVisible = true
                tvStepperNumberFirst.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_inactive)
                tvStepperNumberFirst.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.interpack6
                    )
                )
                tvStepperNumberFirst.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_regular)
            }
            StepperNumberState.ACTIVE -> {
                ivStepperNumberFirst.isVisible = false
                tvStepperNumberFirst.isVisible = true
                tvStepperNumberFirst.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_active)
                tvStepperNumberFirst.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.white
                    )
                )
                tvStepperNumberFirst.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
            }
            StepperNumberState.FINISHED -> {
                ivStepperNumberFirst.isVisible = true
                tvStepperNumberFirst.isVisible = false
                ivStepperNumberFirst.setImageResource(R.drawable.img_stepper_success)
            }
            StepperNumberState.FAILED -> {
                ivStepperNumberFirst.isVisible = true
                tvStepperNumberFirst.isVisible = false
                ivStepperNumberFirst.setImageResource(R.drawable.img_stepper_failed)
            }
        }
    }

    fun secondStepper(state: StepperNumberState) {
        when (state) {
            StepperNumberState.INACTIVE -> {
                ivStepperNumberSecond.isVisible = false
                tvStepperNumberSecond.isVisible = true
                tvStepperNumberSecond.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_inactive)
                tvStepperNumberSecond.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.interpack6
                    )
                )
                tvStepperNumberSecond.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_regular)
            }
            StepperNumberState.ACTIVE -> {
                ivStepperNumberSecond.isVisible = false
                tvStepperNumberSecond.isVisible = true
                tvStepperNumberSecond.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_active)
                tvStepperNumberSecond.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.white
                    )
                )
                tvStepperNumberSecond.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
            }
            StepperNumberState.FINISHED -> {
                ivStepperNumberSecond.isVisible = true
                tvStepperNumberSecond.isVisible = false
                ivStepperNumberSecond.setImageResource(R.drawable.img_stepper_success)
            }
            StepperNumberState.FAILED -> {
                ivStepperNumberSecond.isVisible = true
                tvStepperNumberSecond.isVisible = false
                ivStepperNumberSecond.setImageResource(R.drawable.img_stepper_failed)
            }
        }
    }

    fun thirdStepper(state: StepperNumberState) {
        when (state) {
            StepperNumberState.INACTIVE -> {
                ivStepperNumberThird.isVisible = false
                tvStepperNumberThird.isVisible = true
                tvStepperNumberThird.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_inactive)
                tvStepperNumberThird.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.interpack6
                    )
                )
                tvStepperNumberThird.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_regular)
            }
            StepperNumberState.ACTIVE -> {
                ivStepperNumberThird.isVisible = false
                tvStepperNumberThird.isVisible = true
                tvStepperNumberThird.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_number_active)
                tvStepperNumberThird.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.white
                    )
                )
                tvStepperNumberThird.typeface =
                    ResourcesCompat.getFont(context, R.font.poppins_semi_bold)
            }
            StepperNumberState.FINISHED -> {
                ivStepperNumberThird.isVisible = true
                tvStepperNumberThird.isVisible = false
                ivStepperNumberThird.setImageResource(R.drawable.img_stepper_success)
            }
            StepperNumberState.FAILED -> {
                ivStepperNumberThird.isVisible = true
                tvStepperNumberThird.isVisible = false
                ivStepperNumberThird.setImageResource(R.drawable.img_stepper_failed)
            }
        }
    }
}
