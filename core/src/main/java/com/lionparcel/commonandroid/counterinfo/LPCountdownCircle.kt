package com.lionparcel.commonandroid.counterinfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutCounterInfoCountdownCircleBinding

class LPCountdownCircle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LpLayoutCounterInfoCountdownCircleBinding =
        LpLayoutCounterInfoCountdownCircleBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPCountdownCircle,
            0,
            0
        ).apply {
            try {

            } finally {
                recycle()
            }
        }
        binding.root
    }

    fun getCircleTimer(): ProgressBar = binding.pbCountdownTimer

    fun getTextTimer(): TextView = binding.tvCountdownCircle
}