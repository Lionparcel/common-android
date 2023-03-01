package com.lionparcel.commonandroid.badge

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPBadgeDot @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttrs) {

    private var color: Int

    init {
        context.obtainStyledAttributes(attrs, R.styleable.LPBadgeDot).apply {
            try {
                color = getInt(R.styleable.LPBadgeDot_dotColor, 0)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        val color = when (color) {
            0 -> R.color.interpack7
            1 -> R.color.white
            2 -> R.color.yellow4
            else -> R.color.interpack7
        }
        drawBadge(context, width/ 2F, canvas, color)
        super.onDraw(canvas)
    }

    companion object {

        private fun drawBadge(context: Context, radius: Float, canvas: Canvas?, color: Int) {
            val strokeWidth = 0F
            val innerCircle = radius * 0.9F
            val paint = Paint().apply {
                isAntiAlias = true
            }

            paint.color = ContextCompat.getColor(context, color)
            paint.style = Paint.Style.FILL
            paint.strokeWidth = strokeWidth
            canvas?.drawCircle(
                radius,
                radius,
                innerCircle,
                paint
            )
        }
    }
}