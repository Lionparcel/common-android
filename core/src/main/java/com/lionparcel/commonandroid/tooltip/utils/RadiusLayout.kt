package com.lionparcel.commonandroid.tooltip.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.Px

class RadiusLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs : Int = 0
) : LinearLayout(context, attrs, defStyleAttrs){

    private val path = Path()

    @get:Px
    var radius: Float = 12F

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.apply {
            addRoundRect(
                RectF(0f, 0f, w.toFloat(), h.toFloat()),
                radius,
                radius,
                Path.Direction.CW
            )
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
    }
}
