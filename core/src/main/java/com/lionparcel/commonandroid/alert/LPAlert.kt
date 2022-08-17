package com.lionparcel.commonandroid.alert

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.alert.utils.AlertState
import com.lionparcel.commonandroid.databinding.LpLayoutAlertBinding

class LPAlert @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : LinearLayout(context, attrs, defStyleAttrs) {

    private val binding: LpLayoutAlertBinding = LpLayoutAlertBinding.inflate(LayoutInflater.from(context), this, true)

    private var size : Int

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPAlert,
            0,
            0
        ).apply {
            try {
                size = getInt(R.styleable.LPAlert_alertSize, 0)
            } finally {
                recycle()
            }
        }
        binding.root
        setAlertSize()
    }

    private fun setAlertSize() {
        val scale = resources.displayMetrics.density
        when (size) {
            0 -> {
                binding.llAlert.setPadding((16 * scale).toInt(), (12 * scale).toInt(), (16 * scale).toInt(), (12 * scale).toInt())
                binding.iconStartAlert.apply {
                    val params = LayoutParams(this.layoutParams)
                    layoutParams.height = (20 * scale).toInt()
                    layoutParams.width = (20 * scale).toInt()
                    params.setMargins(0, 0,(12 * scale).toInt(), 0)
                }
                binding.titleAlert.textSize = 12F
                binding.iconEndAlert.apply {
                    val params = LayoutParams(this.layoutParams)
                    layoutParams.height = (20 * scale).toInt()
                    layoutParams.width = (20 * scale).toInt()
                    params.setMargins((12 * scale).toInt(), 0,0, 0)
                }
            }
            1 -> {
                binding.llAlert.setPadding((8 * scale).toInt(), (8 * scale).toInt(), (8 * scale).toInt(), (8 * scale).toInt())
                binding.iconStartAlert.apply {
                    val params = LayoutParams(this.layoutParams)
                    layoutParams.height = (16 * scale).toInt()
                    layoutParams.width = (16 * scale).toInt()
                    params.setMargins(0, 0,(8 * scale).toInt(), 0)
                }
                binding.titleAlert.textSize = 10F
                binding.iconEndAlert.apply {
                    val params = LayoutParams(this.layoutParams)
                    layoutParams.height = (16 * scale).toInt()
                    layoutParams.width = (16 * scale).toInt()
                    params.setMargins((8 * scale).toInt(), 0,0, 0)
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun setAlertState(state: AlertState) {
        val scale = resources.displayMetrics.density
        val cornerRadius = (8 * scale)
        val strokeWidth = (2 * scale)
        val dashWidth = (8 * scale)
        val dashGap = (8 * scale)
        when (state) {
            AlertState.NORMAL -> {
                val shapeDrawable = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = strokeWidth
                    paint.pathEffect = DashPathEffect(floatArrayOf(dashGap, dashWidth),0F)
                    paint.color = resources.getColor(R.color.shades2)
                }
                val shapeFill = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.FILL_AND_STROKE
                    paint.color = resources.getColor(R.color.white)
                }
                val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                binding.llAlert.background = composite
                binding.titleAlert.setTextColor(resources.getColor(R.color.shades5))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.shades5))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.shades5))
            }
            AlertState.WARNING -> {
                val shapeDrawable = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = strokeWidth
                    paint.pathEffect = DashPathEffect(floatArrayOf(dashGap, dashWidth), dashWidth)
                    paint.color = resources.getColor(R.color.yellow6)
                }
                val shapeFill = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.FILL_AND_STROKE
                    paint.color = resources.getColor(R.color.yellow1)
                }
                val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                binding.llAlert.background = composite
                binding.titleAlert.setTextColor(resources.getColor(R.color.box7))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.yellow6))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.yellow6))
            }
            AlertState.DANGER -> {
                val shapeDrawable = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = strokeWidth
                    paint.pathEffect = DashPathEffect(floatArrayOf(dashGap, dashWidth), dashWidth)
                    paint.color = resources.getColor(R.color.interpack6)
                }
                val shapeFill = ShapeDrawable().apply {
                    shape = RoundRectShape(floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius), null, null)
                    paint.style = Paint.Style.FILL_AND_STROKE
                    paint.color = resources.getColor(R.color.red1)
                }
                val composite = LayerDrawable(arrayOf(shapeFill, shapeDrawable))
                binding.llAlert.background = composite
                binding.titleAlert.setTextColor(resources.getColor(R.color.interpack6))
                ImageViewCompat.setImageTintList(binding.iconStartAlert, resources.getColorStateList(R.color.interpack6))
                ImageViewCompat.setImageTintList(binding.iconEndAlert, resources.getColorStateList(R.color.interpack6))
            }
        }
    }

    fun setText(text: String) {
        binding.titleAlert.text = text
    }
}