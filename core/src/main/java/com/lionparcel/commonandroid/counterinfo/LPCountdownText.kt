package com.lionparcel.commonandroid.counterinfo

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.counterinfo.utils.CountdownTextState
import com.lionparcel.commonandroid.counterinfo.utils.TimeViewUtils
import com.lionparcel.commonandroid.databinding.LpLayoutCounterInfoCountdownTextBinding

class LPCountdownText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs) {

    private val binding: LpLayoutCounterInfoCountdownTextBinding =
        LpLayoutCounterInfoCountdownTextBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    private var size: Int
    private var counterBackground: Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPCountdownText,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPCountdownText_counterSize, 0)
                counterBackground = getInt(R.styleable.LPCountdownText_counterBackground, 0)
            } finally {
                recycle()
            }
        }
        binding.root
        setSize()
        setBackground()
    }

    private fun setSize() {
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> getCounterTextView().setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                resources.getDimension(R.dimen.sp_10) / scale
            )
            1 -> getCounterTextView().setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                resources.getDimension(R.dimen.sp_12) / scale
            )
            2 -> getCounterTextView().setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                resources.getDimension(R.dimen.sp_34) / scale
            )
        }
    }

    private fun setBackground() {
        when (counterBackground) {
            0 -> binding.flCountDownText.background = ResourcesCompat.getDrawable(resources, R.drawable.bg_countdown_text_normal_rounded_4, null)
            1 -> binding.flCountDownText.background = ResourcesCompat.getDrawable(resources, R.color.transparent, null)
            2 -> {
                binding.flCountDownText.background = ResourcesCompat.getDrawable(resources, R.color.transparent, null)
                getCounterTextView().setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            }
        }
    }

    fun setCountdownState(state: CountdownTextState) {
        when (state) {
            CountdownTextState.NORMAL -> getCounterTextView().setTextColor(ResourcesCompat.getColor(resources, R.color.shades5, null))
            CountdownTextState.DANGER -> getCounterTextView().setTextColor(ResourcesCompat.getColor(resources, R.color.interpack6, null))
        }
    }

    fun setTime(countDownTime: Long) {
        getCounterTextView().text = TimeViewUtils.setCountDownTimeTextWithHours(countDownTime)
    }

    fun getCounterTextView(): TextView = binding.tvCountDownText
}