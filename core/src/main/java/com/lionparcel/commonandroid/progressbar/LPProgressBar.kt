package com.lionparcel.commonandroid.progressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutProgressBarBinding

class LPProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LpLayoutProgressBarBinding = LpLayoutProgressBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var size: Int

    private var withStatus: Boolean

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPProgressBar,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPProgressBar_progressSize, 0)
                withStatus = getBoolean(R.styleable.LPProgressBar_withStatus, false)
            } finally {
                recycle()
            }
        }
        binding.root
        setProgressBarSize()
        setStatusIndicator()
    }

    private fun setProgressBarSize() {
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> binding.progressBar.layoutParams.height = (8 * scale).toInt()
            1 -> binding.progressBar.layoutParams.height = (4 * scale).toInt()
            2 -> binding.progressBar.layoutParams.height = (2 * scale).toInt()
        }
    }

    private fun setStatusIndicator() {
        binding.llIndicatorDynamic.isVisible = withStatus
        binding.flIndicatorFix.isVisible = withStatus
    }

    fun setProgress(progress: Int) {
        binding.progressBar.progress = progress
    }

    fun setDynamicIndicatorProgress(progress: Float) {
        val params = binding.llIndicatorDynamic.layoutParams as LayoutParams
        if (progress > 100F) {
            params.horizontalBias = 100F/100F
        } else {
            params.horizontalBias = progress/100F
        }
        binding.llIndicatorDynamic.layoutParams = params
        if (progress > 90F) {
            binding.flIndicatorFix.isVisible = false
        }
    }

    fun setDynamicIndicatorTitle(title: String) {
        binding.tvIndicatorDynamicText.text = title
    }

    fun setFixIndicatorTitle(title: String) {
        binding.tvIndicatorFixText.text = title
    }
}
