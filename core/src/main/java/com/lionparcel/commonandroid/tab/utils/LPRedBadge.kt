package com.lionparcel.commonandroid.tab.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

@SuppressLint("AppCompatCustomView")
class LPRedBadge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        drawBadge(context, width/ 2F, canvas)
        super.onDraw(canvas)
    }

    companion object {

        private fun drawBadge(context: Context, radius: Float, canvas: Canvas?) {
            val strokeWidth = 0F
            val innerCircle = radius * 0.8F
            val ringWidth = radius * 0.2F
            val paint = Paint().apply {
                isAntiAlias = true
            }

            paint.color = ContextCompat.getColor(context, R.color.interpack7)
            paint.style = Paint.Style.FILL
            paint.strokeWidth = strokeWidth
            canvas?.drawCircle(
                radius,
                radius,
                innerCircle,
                paint
            )

            paint.color = Color.WHITE
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = ringWidth
            canvas?.drawCircle(
                radius,
                radius,
                innerCircle,
                paint
            )
        }
    }
}
