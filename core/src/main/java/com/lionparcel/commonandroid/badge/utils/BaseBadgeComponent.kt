package com.lionparcel.commonandroid.badge.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutBadgeViewBinding
import com.lionparcel.commonandroid.walkthrough.utils.toDp

abstract class BaseBadgeComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs) {

    enum class Type(
        val styleableId: IntArray,
        val color: Int,
        val isStrokeOutside: Int
    ) {
        NUMBER(
            R.styleable.LPBadgeNumber,
            R.styleable.LPBadgeNumber_badgeColor,
            R.styleable.LPBadgeNumber_isStrokeOutside
        ),
        TEXT(
            R.styleable.LPBadgeText,
            R.styleable.LPBadgeText_badgeColor,
            R.styleable.LPBadgeText_isStrokeOutside
        )
    }

    private var binding: LpLayoutBadgeViewBinding =
        LpLayoutBadgeViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var color: Int = 0

    private var isStrokeOutside: Boolean = false

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, type.styleableId).apply {
                try {
                    color = getInt(type.color, color)
                    isStrokeOutside = getBoolean(type.isStrokeOutside, isStrokeOutside)
                } finally {
                    recycle()
                }
            }
        }
        binding.root
        binding.tvBadge.setTextColor()
        setBackground(18.dpToF())
    }

    private fun setBackground(radius: Float) {
        val bitmap = createBitmap(16, 16)
        val canvas = Canvas(bitmap)
        val gradientDrawable = GradientDrawable()
        val color = when (color) {
            0 -> R.color.pensive3
            1 -> R.color.interpack7
            2 -> R.color.white
            3 -> R.color.yellow4
            else -> R.color.pensive3
        }

        gradientDrawable.setColor(ContextCompat.getColor(context, color))
        gradientDrawable.cornerRadius = radius
        if (isStrokeOutside) {
            when (this.color) {
                2 -> gradientDrawable.setStroke(
                    2.toDp(),
                    ContextCompat.getColor(context, R.color.interpack7)
                )
                else -> gradientDrawable.setStroke(
                    2.toDp(),
                    ContextCompat.getColor(context, R.color.white)
                )
            }
            binding.tvBadge.apply {
                val params = this.layoutParams as MarginLayoutParams
                params.height = 20.toDp()
                layoutParams = params
            }
            this.apply {
                setPadding(6, 1, 6, 0)
                minimumWidth = 20.toDp()
            }
        }
        gradientDrawable.draw(canvas)
        this.background = gradientDrawable
    }

    private fun TextView.setTextColor() {
        val color = when (color) {
            0 -> R.color.shades5
            1 -> R.color.white
            2 -> R.color.interpack7
            3 -> R.color.shades5
            else -> R.color.shades5
        }
        this.setTextColor(ContextCompat.getColor(context, color))
    }

    private fun Int.dpToF(): Float = (this * resources.displayMetrics.density)


    protected fun setText(text: String) {
        binding.tvBadge.text = text
    }

    abstract val type: Type
}