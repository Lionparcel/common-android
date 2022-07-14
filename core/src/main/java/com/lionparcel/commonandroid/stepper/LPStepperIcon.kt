package com.lionparcel.commonandroid.stepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.stepper.utils.StepperIconState

class LPStepperIcon : ConstraintLayout {

    private var textVisibility: Int
    private var firstTitle : String
    private var secondTitle : String
    private var thirdTitle : String
    private var fourthTitle : String

    private val ivStepperIconFirstIcon: ImageView
    private val ivStepperIconSecondIcon: ImageView
    private val ivStepperIconThirdIcon: ImageView
    private val ivStepperIconFourthIcon: ImageView
    private val ivStepperIconFirstBadge: ImageView
    private val ivStepperIconSecondBadge: ImageView
    private val ivStepperIconThirdBadge: ImageView
    private val ivStepperIconFourthBadge: ImageView
    private val tvStepperIconFirstTitle: TextView
    private val tvStepperIconSecondTitle: TextView
    private val tvStepperIconThirdTitle: TextView
    private val tvStepperIconFourthTitle: TextView
    private val vwStepperIconLineOne: View
    private val vwStepperIconLineTwo: View
    private val vwStepperIconLineThree: View

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_stepper_icon, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPStepperIcon,
            0,
            0
        ).apply {
            try {
                textVisibility = getInt(R.styleable.LPStepperIcon_stepperText, 0)
                firstTitle = getString(R.styleable.LPStepperIcon_firstTitle)?: resources.getString(R.string.stepper_icon_title_one)
                secondTitle = getString(R.styleable.LPStepperIcon_secondTitle)?: resources.getString(R.string.stepper_icon_title_two)
                thirdTitle = getString(R.styleable.LPStepperIcon_thirdTitle)?: resources.getString(R.string.stepper_icon_title_three)
                fourthTitle = getString(R.styleable.LPStepperIcon_fourthTitle)?: resources.getString(R.string.stepper_icon_title_four)
            } finally {
                recycle()
            }
        }

        ivStepperIconFirstIcon = findViewById(R.id.iv_stepper_icon_first_icon)
        ivStepperIconSecondIcon = findViewById(R.id.iv_stepper_icon_second_icon)
        ivStepperIconThirdIcon = findViewById(R.id.iv_stepper_icon_third_icon)
        ivStepperIconFourthIcon = findViewById(R.id.iv_stepper_icon_fourth_icon)
        ivStepperIconFirstBadge = findViewById(R.id.iv_stepper_icon_first_badge)
        ivStepperIconSecondBadge = findViewById(R.id.iv_stepper_icon_second_badge)
        ivStepperIconThirdBadge = findViewById(R.id.iv_stepper_icon_third_badge)
        ivStepperIconFourthBadge = findViewById(R.id.iv_stepper_icon_fourth_badge)
        tvStepperIconFirstTitle = findViewById(R.id.tv_stepper_icon_first_title)
        tvStepperIconSecondTitle = findViewById(R.id.tv_stepper_icon_second_title)
        tvStepperIconThirdTitle = findViewById(R.id.tv_stepper_icon_third_title)
        tvStepperIconFourthTitle = findViewById(R.id.tv_stepper_icon_fourth_title)
        vwStepperIconLineOne = findViewById(R.id.vw_stepper_icon_line_one)
        vwStepperIconLineTwo = findViewById(R.id.vw_stepper_icon_line_two)
        vwStepperIconLineThree = findViewById(R.id.vw_stepper_icon_line_three)

        setTextVisibility()
        setTitleText()
    }

    private fun setTextVisibility() {
        when (textVisibility) {
            0 -> {
                tvStepperIconFirstTitle.isVisible = true
                tvStepperIconSecondTitle.isVisible = true
                tvStepperIconThirdTitle.isVisible = true
                tvStepperIconFourthTitle.isVisible = true
            }
            1 -> {
                tvStepperIconFirstTitle.isVisible = false
                tvStepperIconSecondTitle.isVisible = false
                tvStepperIconThirdTitle.isVisible = false
                tvStepperIconFourthTitle.isVisible = false
            }
        }
    }

    private fun setTitleText() {
        tvStepperIconFirstTitle.text = firstTitle
        tvStepperIconSecondTitle.text = secondTitle
        tvStepperIconThirdTitle.text = thirdTitle
        tvStepperIconFourthTitle.text = fourthTitle
    }

    fun firstIcon(state: StepperIconState) {
        when (state) {
            StepperIconState.INACTIVE -> {
                ivStepperIconFirstIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_inactive)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFirstIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconFirstBadge.isVisible = false
            }
            StepperIconState.ACTIVE -> {
                ivStepperIconFirstIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFirstIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconFirstBadge.isVisible = false
            }
            StepperIconState.SUCCESS -> {
                ivStepperIconFirstIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFirstIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFirstBadge.isVisible = true
                ivStepperIconFirstBadge.setImageResource(R.drawable.ic_stepper_badge_success)
            }
            StepperIconState.FAILED -> {
                ivStepperIconFirstIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFirstIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFirstBadge.isVisible = true
                ivStepperIconFirstBadge.setImageResource(R.drawable.ic_stepper_badge_failed)
            }
            StepperIconState.WARNING -> {
                ivStepperIconFirstIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFirstIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFirstBadge.isVisible = true
                ivStepperIconFirstBadge.setImageResource(R.drawable.ic_stepper_badge_warning)
            }
        }
    }

    fun secondIcon(state: StepperIconState) {
        when (state) {
            StepperIconState.INACTIVE -> {
                ivStepperIconSecondIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_inactive)
                ImageViewCompat.setImageTintList(
                    ivStepperIconSecondIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconSecondBadge.isVisible = false
                vwStepperIconLineOne.background = ContextCompat.getDrawable(context, R.color.transparent)
            }
            StepperIconState.ACTIVE -> {
                ivStepperIconSecondIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active)
                ImageViewCompat.setImageTintList(
                    ivStepperIconSecondIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconSecondBadge.isVisible = false
                vwStepperIconLineOne.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.SUCCESS -> {
                ivStepperIconSecondIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconSecondIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconSecondBadge.isVisible = true
                ivStepperIconSecondBadge.setImageResource(R.drawable.ic_stepper_badge_success)
                vwStepperIconLineOne.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.FAILED -> {
                ivStepperIconSecondIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconSecondIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconSecondBadge.isVisible = true
                ivStepperIconSecondBadge.setImageResource(R.drawable.ic_stepper_badge_failed)
                vwStepperIconLineOne.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.WARNING -> {
                ivStepperIconSecondIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconSecondIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconSecondBadge.isVisible = true
                ivStepperIconSecondBadge.setImageResource(R.drawable.ic_stepper_badge_warning)
                vwStepperIconLineOne.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
        }
    }

    fun thirdIcon(state: StepperIconState) {
        when (state) {
            StepperIconState.INACTIVE -> {
                ivStepperIconThirdIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_inactive)
                ImageViewCompat.setImageTintList(
                    ivStepperIconThirdIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconThirdBadge.isVisible = false
                vwStepperIconLineTwo.background = ContextCompat.getDrawable(context, R.color.transparent)
            }
            StepperIconState.ACTIVE -> {
                ivStepperIconThirdIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active)
                ImageViewCompat.setImageTintList(
                    ivStepperIconThirdIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconThirdBadge.isVisible = false
                vwStepperIconLineTwo.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.SUCCESS -> {
                ivStepperIconThirdIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconThirdIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconThirdBadge.isVisible = true
                ivStepperIconThirdBadge.setImageResource(R.drawable.ic_stepper_badge_success)
                vwStepperIconLineTwo.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.FAILED -> {
                ivStepperIconThirdIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconThirdIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconThirdBadge.isVisible = true
                ivStepperIconThirdBadge.setImageResource(R.drawable.ic_stepper_badge_failed)
                vwStepperIconLineTwo.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.WARNING -> {
                ivStepperIconThirdIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconThirdIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconThirdBadge.isVisible = true
                ivStepperIconThirdBadge.setImageResource(R.drawable.ic_stepper_badge_warning)
                vwStepperIconLineTwo.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
        }
    }

    fun fourthIcon(state: StepperIconState) {
        when (state) {
            StepperIconState.INACTIVE -> {
                ivStepperIconFourthIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_inactive)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFourthIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconFourthBadge.isVisible = false
                vwStepperIconLineThree.background = ContextCompat.getDrawable(context, R.color.transparent)
            }
            StepperIconState.ACTIVE -> {
                ivStepperIconFourthIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFourthIcon,
                    ContextCompat.getColorStateList(context, R.color.shades5)
                )
                ivStepperIconFourthBadge.isVisible = false
                vwStepperIconLineThree.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.SUCCESS -> {
                ivStepperIconFourthIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFourthIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFourthBadge.isVisible = true
                ivStepperIconFourthBadge.setImageResource(R.drawable.ic_stepper_badge_success)
                vwStepperIconLineThree.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.FAILED -> {
                ivStepperIconFourthIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFourthIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFourthBadge.isVisible = true
                ivStepperIconFourthBadge.setImageResource(R.drawable.ic_stepper_badge_failed)
                vwStepperIconLineThree.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
            StepperIconState.WARNING -> {
                ivStepperIconFourthIcon.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_active_with_badge)
                ImageViewCompat.setImageTintList(
                    ivStepperIconFourthIcon,
                    ContextCompat.getColorStateList(context, R.color.white)
                )
                ivStepperIconFourthBadge.isVisible = true
                ivStepperIconFourthBadge.setImageResource(R.drawable.ic_stepper_badge_warning)
                vwStepperIconLineThree.background = ContextCompat.getDrawable(context, R.drawable.bg_stepper_icon_line)
            }
        }
    }
}