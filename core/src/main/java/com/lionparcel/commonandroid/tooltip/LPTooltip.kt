package com.lionparcel.commonandroid.tooltip

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.window.layout.WindowMetricsCalculator
import com.lionparcel.commonandroid.R

class LPTooltip(
    context: Context,
    parentActivity: Activity,
    themeStyleRes: Int = R.style.LPTooltipDialogTheme
) : Dialog(context, themeStyleRes) {

    private val ttContentView: ConstraintLayout
    private val ttContainer: ConstraintLayout
    private val ttUpArrow: ImageView
    private val ttDownArrow: ImageView
    private val ttLeftArrow: ImageView
    private val ttRightArrow: ImageView
    private val ttDialogBox: LinearLayout
    private val ttContent: TextView
    private val ttClose: ImageView
    private var windowHeight: Int
    private var windowWidth: Int
    private var statusBarHeight: Int
    private val constraintSet = ConstraintSet()

    private var content: String = ""

    private val activity = parentActivity

    init {
        setContentView(R.layout.lp_layout_tooltip_body)
        ttContentView = findViewById(R.id.ttContentView)
        ttContainer = findViewById(R.id.ttContainer)
        ttUpArrow = findViewById(R.id.ttTopArrow)
        ttDownArrow = findViewById(R.id.ttBottomArrow)
        ttLeftArrow = findViewById(R.id.ttLeftArrow)
        ttRightArrow = findViewById(R.id.ttRightArrow)
        ttDialogBox = findViewById(R.id.ttDialogBox)
        ttContent = findViewById(R.id.ttContent)
        ttClose = findViewById(R.id.ttClose)

        val usableView = parentActivity.window.findViewById<View>(Window.ID_ANDROID_CONTENT)
        windowHeight = usableView.height
        windowWidth = usableView.width
        statusBarHeight = getScreenHeight(context) - windowHeight

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        ttContentView.setOnClickListener {
            dismiss()
        }
        ttClose.setOnClickListener {
            dismiss()
        }
    }

    fun pointToUpDown(
        x: Int,
        y: Int,
        position: PositionVertical,
        alignment: HorizontalArrowAlignment
    ): LPTooltip {
        val params = ttContainer.layoutParams as ConstraintLayout.LayoutParams

        when (position) {
            PositionVertical.ABOVE -> {
                params.bottomMargin = windowHeight - y - statusBarHeight + dpToPx(14F)
                if (x >= 0) {
                    pointArrowUpDownTo(ttDownArrow, alignment)
                }
                val xPosition = x.toDouble() / screenWidth().toDouble()
                constraintSet.clone(ttContentView)
                constraintSet.clear(R.id.ttContainer, ConstraintSet.TOP)
                constraintSet.setHorizontalBias(R.id.ttContainer, xPosition.toFloat())
                constraintSet.applyTo(ttContentView)
            }
            PositionVertical.BELOW -> {
                params.topMargin = y - statusBarHeight
                if (x >= 0) {
                    pointArrowUpDownTo(ttUpArrow, alignment)
                }
                val xPosition = x.toDouble() / screenWidth().toDouble()
                constraintSet.clone(ttContentView)
                constraintSet.clear(R.id.ttContainer, ConstraintSet.BOTTOM)
                constraintSet.setHorizontalBias(R.id.ttContainer, xPosition.toFloat())
                constraintSet.applyTo(ttContentView)
            }
        }

        ttContainer.layoutParams = params
        return this
    }

    fun pointToLeftRight(
        x: Int,
        y: Int,
        position: PositionHorizontal,
        alignment: VerticalArrowAlignment
    ): LPTooltip {
        val params = ttContainer.layoutParams as ConstraintLayout.LayoutParams

        when (position) {
            PositionHorizontal.RIGHT -> {
                params.marginStart = x
                if (y >= 0) {
                    pointArrowLeftRightTo(ttLeftArrow, alignment)
                }
                val yPosition = (y - statusBarHeight + dpToPx(16F)).toDouble() / getScreenHeight(context).toDouble()
                constraintSet.clone(ttContentView)
                constraintSet.clear(R.id.ttContainer, ConstraintSet.END)
                constraintSet.setVerticalBias(R.id.ttContainer, yPosition.toFloat())
                constraintSet.applyTo(ttContentView)
            }
            PositionHorizontal.LEFT -> {
                params.marginEnd = windowWidth - x
                if (y >= 0) {
                    pointArrowLeftRightTo(ttRightArrow, alignment)
                }
                val yPosition = (y - statusBarHeight + dpToPx(16F)).toDouble() / getScreenHeight(context).toDouble()
                constraintSet.clone(ttContentView)
                constraintSet.clear(R.id.ttContainer, ConstraintSet.START)
                constraintSet.setVerticalBias(R.id.ttContainer, yPosition.toFloat())
                constraintSet.applyTo(ttContentView)
            }
        }

        ttContainer.layoutParams = params
        return this
    }

    private fun pointArrowUpDownTo(arrow: ImageView, alignment: HorizontalArrowAlignment) {
        constraintSet.clone(ttContainer)
        when (alignment) {
            HorizontalArrowAlignment.LEFT -> {
                constraintSet.setHorizontalBias(R.id.ttTopArrow, 0.125f)
                constraintSet.setHorizontalBias(R.id.ttBottomArrow, 0.125f)
            }
            HorizontalArrowAlignment.CENTER_LEFT -> {
                constraintSet.setHorizontalBias(R.id.ttTopArrow, 0.375f)
                constraintSet.setHorizontalBias(R.id.ttBottomArrow, 0.375f)
            }
            HorizontalArrowAlignment.CENTER -> {
                constraintSet.setHorizontalBias(R.id.ttTopArrow, 0.5f)
                constraintSet.setHorizontalBias(R.id.ttBottomArrow, 0.5f)
            }
            HorizontalArrowAlignment.CENTER_RIGHT -> {
                constraintSet.setHorizontalBias(R.id.ttTopArrow, 0.625f)
                constraintSet.setHorizontalBias(R.id.ttBottomArrow, 0.625f)
            }
            HorizontalArrowAlignment.RIGHT -> {
                constraintSet.setHorizontalBias(R.id.ttTopArrow, 0.875f)
                constraintSet.setHorizontalBias(R.id.ttBottomArrow, 0.875f)
            }
        }

        when (arrow) {
            ttUpArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.TOP)
                constraintSet.connect(R.id.ttDialogBox, ConstraintSet.TOP, R.id.ttTopArrow, ConstraintSet.BOTTOM)
            }
            ttDownArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.BOTTOM)
                constraintSet.connect(R.id.ttDialogBox, ConstraintSet.BOTTOM, R.id.ttBottomArrow, ConstraintSet.TOP)
            }
        }

        constraintSet.applyTo(ttContainer)
        arrow.visibility = View.VISIBLE
    }

    private fun pointArrowLeftRightTo(arrow: ImageView, alignment: VerticalArrowAlignment) {
        constraintSet.clone(ttContainer)
        when (alignment) {
            VerticalArrowAlignment.TOP -> {
                constraintSet.setVerticalBias(R.id.ttLeftArrow, 0.25f)
                constraintSet.setVerticalBias(R.id.ttRightArrow, 0.25f)
            }
            VerticalArrowAlignment.CENTER -> {
                constraintSet.setVerticalBias(R.id.ttLeftArrow, 0.5f)
                constraintSet.setVerticalBias(R.id.ttRightArrow, 0.5f)
            }
            VerticalArrowAlignment.BOTTOM -> {
                constraintSet.setVerticalBias(R.id.ttLeftArrow, 0.75f)
                constraintSet.setVerticalBias(R.id.ttRightArrow, 0.75f)
            }
        }

        when (arrow) {
            ttLeftArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.START)
                constraintSet.connect(R.id.ttDialogBox, ConstraintSet.START, R.id.ttLeftArrow, ConstraintSet.END)
            }
            ttRightArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.END)
                constraintSet.connect(R.id.ttDialogBox, ConstraintSet.END, R.id.ttRightArrow, ConstraintSet.START)
            }
        }

        constraintSet.applyTo(ttContainer)
        arrow.visibility = View.VISIBLE
    }

    fun content(content: String): LPTooltip {
        ttContent.text = content
        ttContent.visibility = View.VISIBLE
        this.content = content
        return this
    }

    fun closeIcon(): LPTooltip {
        ttClose.visibility = View.VISIBLE
        return this
    }

    private fun getScreenHeight(context: Context): Int {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = Point()
        wm.defaultDisplay.getSize(size)
        return size.y
    }

    private fun dpToPx(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun screenWidth(): Int {
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        return windowMetrics.bounds.width()
    }

    enum class PositionVertical {
        ABOVE, BELOW
    }

    enum class PositionHorizontal {
        LEFT, RIGHT
    }

    enum class HorizontalArrowAlignment {
        LEFT, CENTER_LEFT, CENTER, CENTER_RIGHT, RIGHT
    }

    enum class VerticalArrowAlignment {
        TOP, CENTER, BOTTOM
    }

}
