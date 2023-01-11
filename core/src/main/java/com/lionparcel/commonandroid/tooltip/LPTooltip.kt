package com.lionparcel.commonandroid.tooltip

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.annotation.FloatRange
import androidx.annotation.MainThread
import androidx.annotation.Px
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutTooltipBodyBinding
import com.lionparcel.commonandroid.walkthrough.utils.dp

class LPTooltip(
    private val context: Context,
    private val builder: Builder,
) : LifecycleObserver {

    private val binding: LpLayoutTooltipBodyBinding = LpLayoutTooltipBodyBinding.inflate(
        LayoutInflater.from(context), null, false
    )

    private val popupWindow: PopupWindow
    private val constraintSet = ConstraintSet()

    init {
        this.popupWindow = PopupWindow(
            binding.root,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        if (context is LifecycleOwner) context.lifecycle.addObserver(this)
        adjustFitsSystemWindows(binding.root)

        this.popupWindow.isFocusable = true
        this.popupWindow.setTouchInterceptor(
            object : View.OnTouchListener {
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    if (event.action == MotionEvent.ACTION_OUTSIDE) {
                        this@LPTooltip.dismiss()
                        return true
                    }
                    return false
                }
            }
        )
        this.popupWindow.setOnDismissListener {
            builder.onDismissListener?.invoke()
        }
    }

    private fun adjustFitsSystemWindows(parent: ViewGroup) {
        parent.fitsSystemWindows = false
        (0 until parent.childCount).map { parent.getChildAt(it) }.forEach { child ->
            child.fitsSystemWindows = false
            if (child is ViewGroup) {
                adjustFitsSystemWindows(child)
            }
        }
    }

    private fun pointArrowUpDownTo(arrow: ImageView) {
        constraintSet.clone(binding.ttContainer)
        constraintSet.setHorizontalBias(R.id.ttTopArrow, builder.arrowPosition)
        constraintSet.setHorizontalBias(R.id.ttBottomArrow, builder.arrowPosition)

        when (arrow) {
            binding.ttTopArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.TOP)
                constraintSet.connect(
                    R.id.ttDialogBox,
                    ConstraintSet.TOP,
                    R.id.ttTopArrow,
                    ConstraintSet.BOTTOM
                )
            }
            binding.ttBottomArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.BOTTOM)
                constraintSet.connect(
                    R.id.ttDialogBox,
                    ConstraintSet.BOTTOM,
                    R.id.ttBottomArrow,
                    ConstraintSet.TOP
                )
            }
        }

        constraintSet.applyTo(binding.ttContainer)
        arrow.visibility = View.VISIBLE
    }

    private fun pointArrowLeftRightTo(arrow: ImageView) {
        constraintSet.clone(binding.ttContainer)
        constraintSet.setVerticalBias(R.id.ttLeftArrow, builder.arrowPosition)
        constraintSet.setVerticalBias(R.id.ttRightArrow, builder.arrowPosition)

        when (arrow) {
            binding.ttLeftArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.START)
                constraintSet.connect(
                    R.id.ttDialogBox,
                    ConstraintSet.START,
                    R.id.ttLeftArrow,
                    ConstraintSet.END
                )
            }
            binding.ttRightArrow -> {
                constraintSet.clear(R.id.ttDialogBox, ConstraintSet.END)
                constraintSet.connect(
                    R.id.ttDialogBox,
                    ConstraintSet.END,
                    R.id.ttRightArrow,
                    ConstraintSet.START
                )
            }
        }

        constraintSet.applyTo(binding.ttContainer)
        arrow.visibility = View.VISIBLE
    }

    private fun Context.displaySize(): Point {
        return Point(
            resources.displayMetrics.widthPixels,
            resources.displayMetrics.heightPixels
        )
    }

    private fun dismiss() {
        this.popupWindow.dismiss()
    }

    private inline fun show(anchor: View, crossinline block: () -> Unit) {
        anchor.post {
            binding.ttClose.setOnClickListener {
                this@LPTooltip.popupWindow.dismiss()
            }
            this.binding.ttContent.text = builder.text
            this.binding.ttClose.isVisible = builder.isCloseIcon
            this.binding.root.measure(
                View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED
            )
            this.popupWindow.width = getMeasuredWidth()
            this.popupWindow.height = getMeasuredHeight()

            block()
        }
    }

    fun showFromAbove(anchor: View, xOff: Int = 0, yOff: Int = 0) {
        show(anchor) {
            pointArrowUpDownTo(binding.ttBottomArrow)
            popupWindow.showAsDropDown(
                anchor,
                ((anchor.measuredWidth / 2) - (getMeasuredWidth() / 2) + xOff),
                -getMeasuredHeight() - anchor.measuredHeight + yOff
            )
        }
    }

    fun showFromBelow(anchor: View, xOff: Int = 0, yOff: Int = 0) {
        show(anchor) {
            pointArrowUpDownTo(binding.ttTopArrow)
            popupWindow.showAsDropDown(
                anchor,
                ((anchor.measuredWidth / 2) - (getMeasuredWidth() / 2) + xOff),
                yOff
            )
        }
    }

    fun showFromRight(anchor: View, xOff: Int = 0, yOff: Int = 0) {
        show(anchor) {
            binding.root.setPadding(0,0,0,0)
            pointArrowLeftRightTo(binding.ttLeftArrow)
            popupWindow.showAsDropDown(
                anchor,
                anchor.measuredWidth + xOff,
                -(getMeasuredHeight() / 2) - (anchor.measuredHeight / 2) + yOff
            )
        }
    }

    fun showFromLeft(anchor: View, xOff: Int = 0, yOff: Int = 0) {
        show(anchor) {
            binding.root.setPadding(0,0,0,0)
            pointArrowLeftRightTo(binding.ttRightArrow)
            popupWindow.showAsDropDown(
                anchor,
                -(getMeasuredWidth()) + xOff,
                -(getMeasuredHeight() / 2) - (anchor.measuredHeight / 2) + yOff
            )
        }
    }

    fun getMeasuredWidth(): Int {
        val displayWidth = context.displaySize().x
        return builder.width.coerceAtMost(displayWidth)
    }

    fun getMeasuredHeight(): Int {
        return builder.height
    }

    @LPTooltipInlineDsl
    class Builder(private val context: Context) {

        @JvmField
        @Px
        @set:JvmSynthetic
        var width: Int = Int.MIN_VALUE

        @JvmField
        @Px
        @set:JvmSynthetic
        var height: Int = Int.MIN_VALUE

        @JvmField
        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var arrowPosition: Float = 0.5f

        @JvmField
        @set:JvmSynthetic
        var text: CharSequence = ""

        @JvmField
        @set:JvmSynthetic
        var isCloseIcon: Boolean = false

        @JvmField
        @set:JvmSynthetic
        var onDismissListener: (() -> Unit)? = null

        fun build(): LPTooltip = LPTooltip(
            context = context,
            builder = this
        )

        fun setText(value: CharSequence): Builder = apply { this.text = value }

        fun setCloseIcon(value: Boolean): Builder = apply { this.isCloseIcon = value }

        /**
         * Set width and height value is necessary due to LPTooltip position and size
         * If the position of tooltip is not accurate try to resize either width or height
         * Same case to size of tooltip, just resize either width or height
         */
        fun setWidth(value: Int): Builder = apply {
            require(
                value > 0 || value == Int.MIN_VALUE
            )
            this.width = value.dp
        }

        fun setHeight(value: Int): Builder = apply {
            require(
                value > 0 || value == Int.MIN_VALUE
            )
            this.height = value.dp
        }

        fun setArrowPosition(@FloatRange(from = 0.0, to = 1.0) value: Float): Builder = apply {
            this.arrowPosition = value
        }

        fun setOnDismissListener(listener: (() -> Unit)) {
            this.onDismissListener = listener
        }

    }

}

@DslMarker
internal annotation class LPTooltipInlineDsl

@MainThread
@JvmSynthetic
@LPTooltipInlineDsl
inline fun createLPTooltip(
    context: Context,
    crossinline block: LPTooltip.Builder.() -> Unit
): LPTooltip = LPTooltip.Builder(context).apply(block).build()
