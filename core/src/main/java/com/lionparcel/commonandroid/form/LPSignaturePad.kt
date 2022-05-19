package com.lionparcel.commonandroid.form

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.media.ThumbnailUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import com.lionparcel.commonandroid.R
import kotlin.math.abs

private const val STROKE_WIDTH = 12F

class LPSignaturePad @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    companion object {
        const val TOUCH_TOLENRANCE = 4
    }

    // Holds the path you are currently drawing.
    private var path = Path()

    private val drawColor = ResourcesCompat.getColor(resources, R.color.shades5, null)
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.androidDefault, null)
    private var extraCanvas: Canvas? = null
    private var extraBitmap: Bitmap? = null
    private var onStartDrawingListener: (() -> Unit)? = null
    private val defaultBitmapPaint: Paint = Paint(Paint.DITHER_FLAG)

    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)

    }

    fun setOnStartDrawingListener(onDrawingListener: () -> Unit) {
        this.onStartDrawingListener = onDrawingListener
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var currentX = 0f
    private var currentY = 0f

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap!!)
        extraCanvas!!.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Draw the bitmap that has the saved path.
        extraBitmap?.let {
            canvas?.drawBitmap(it, 0F, 0F, defaultBitmapPaint)
            canvas?.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart()
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove()
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    private fun touchStart() {
        onStartDrawingListener?.invoke()
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        if (dx >= TOUCH_TOLENRANCE || dy >= TOUCH_TOLENRANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
        }
    }

    private fun touchUp() {
        path.lineTo(currentX, currentY)
        extraCanvas?.drawPath(path, paint)
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    fun clearDrawing() {
        onSizeChanged(width, height, width, height)
        invalidate()
    }

    fun getDrawingBitmap(): Bitmap? {
        val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        draw(canvas)
        return ThumbnailUtils.extractThumbnail(result, width / 2, height / 2)
    }

}
