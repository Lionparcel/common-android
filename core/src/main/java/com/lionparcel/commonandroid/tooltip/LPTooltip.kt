package com.lionparcel.commonandroid.tooltip

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Looper
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.core.view.ViewCompat
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.LifecycleObserver
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutTooltipBodyBinding
import com.lionparcel.commonandroid.tooltip.utils.*
import com.lionparcel.commonandroid.tooltip.utils.TooltipCenterAlign.Companion.getRTLSupportAlign
import com.lionparcel.commonandroid.tooltip.utils.TooltipOrientation.Companion.getRTLSupportOrientation
import com.lionparcel.commonandroid.tooltip.utils.displaySize
import com.lionparcel.commonandroid.tooltip.utils.dp
import com.lionparcel.commonandroid.tooltip.utils.isFinishing
import kotlin.math.roundToInt

@DslMarker
internal annotation class TooltipInlineDsl

@JvmSynthetic
@TooltipInlineDsl
inline fun createTooltip(
    context: Context,
    crossinline block: LPTooltip.Builder.() -> Unit
): LPTooltip = LPTooltip.Builder(context).apply(block).build()

class LPTooltip(
    private val context: Context,
    private val builder: Builder
) : LifecycleObserver {

    private val binding: LpLayoutTooltipBodyBinding =
        LpLayoutTooltipBodyBinding.inflate(LayoutInflater.from(context), null, false)

    /** main content window popup */
    private val bodyWindow: PopupWindow = PopupWindow(
        binding.root,
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )

    var isShowing: Boolean = false
        private set

    private var destroyed: Boolean = false

    private val handler: android.os.Handler by lazy(LazyThreadSafetyMode.NONE) {
        android.os.Handler(Looper.getMainLooper())
    }

    private val autoDismissRunnable: AutoDismissRunnable by lazy(LazyThreadSafetyMode.NONE) {
        AutoDismissRunnable(this)
    }

    init {
        createByBuilder()
    }

    private fun createByBuilder() {
        initializeBackground()
        initializeTooltipContent()
        initializeText()

        adjustFitSystemWindows(binding.root)
    }

    private fun adjustFitSystemWindows(parent: ViewGroup) {
        parent.fitsSystemWindows = false
        (0 until parent.childCount).map { parent.getChildAt(it) }.forEach { child ->
            child.fitsSystemWindows = false
            if (child is ViewGroup) {
                adjustFitSystemWindows(child)
            }
        }
    }

    private fun getMinArrowPosition(): Float {
        return (builder.arrowSize.toFloat() * builder.arrowAlignAnchorPaddingRatio) + builder.arrowAlignAnchorPadding
    }

    private fun getDoubleArrowSize(): Int {
        return builder.arrowSize * 2
    }

    private fun getArrowConstraintPositionX(anchor: View): Float {
        val tooltipX = binding.tooltipContent.getViewPointOnScreen().x
        val anchorX = anchor.getViewPointOnScreen().x
        return (anchor.width) * builder.arrowPosition + anchorX - tooltipX - builder.arrowHalfSize
    }

    private fun getArrowConstraintPositionY(anchor: View): Float {
        val tooltipY = binding.tooltipContent.getViewPointOnScreen().y
        val anchorY = anchor.getViewPointOnScreen().y
        val arrowHalfSize = builder.arrowSize/2
        return (anchor.width) * builder.arrowPosition + anchorY - tooltipY - arrowHalfSize
    }

    private fun initializeArrow(anchor: View) {
        with(binding.tooltipArrow) {
            layoutParams = FrameLayout.LayoutParams(builder.arrowSize, builder.arrowSize)
            alpha = builder.alpha
            setPadding(
                builder.arrowLeftPadding,
                builder.arrowTopPadding,
                builder.arrowRightPadding,
                builder.arrowBottomPadding
            )
            ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(resources.getColor(R.color.shades5)))
            binding.tooltipCard.post {

                when (builder.tooltipOrientation.getRTLSupportOrientation(builder.isRtlLayout)) {
                    TooltipOrientation.BOTTOM -> {
                        rotation = 180f
                        x = getArrowConstraintPositionX(anchor)
                        y = binding.tooltipCard.y + binding.tooltipCard.height - 1
                        ViewCompat.setElevation(this, builder.arrowElevation)
                    }
                    TooltipOrientation.TOP -> {
                        rotation = 0f
                        x = getArrowConstraintPositionX(anchor)
                        y = binding.tooltipCard.y + builder.arrowSize + 1
                    }
                    TooltipOrientation.START -> {
                        rotation = -90f
                        x = binding.tooltipCard.x - builder.arrowSize + 1
                        y = getArrowConstraintPositionY(anchor)
                    }
                    TooltipOrientation.END -> {
                        rotation = 90f
                        x = binding.tooltipCard.x + binding.tooltipCard.width - 1
                        y = getArrowConstraintPositionY(anchor)
                    }
                }
                visible(builder.isVisibleArrow)
            }
        }
    }

    private fun getMeasureWidth(): Int {
        val displayWidth = displaySize.x
        return when {
            builder.widthRatio != 0F -> (displayWidth * builder.widthRatio).toInt()
            builder.minWidthRatio != 0F || builder.maxWidthRatio != 0F -> {
                val maxWidthRatio =
                    if (builder.maxWidthRatio != 0F) builder.maxWidthRatio else 1F
                binding.root.measuredWidth.coerceIn(
                    (displayWidth * builder.minWidthRatio).toInt(),
                    (displayWidth * maxWidthRatio).toInt()
                )
            }
            builder.width != 0 -> builder.width.coerceAtMost(displayWidth)
            else -> binding.root.measuredWidth.coerceIn(builder.minWidth, builder.maxWidth)
        }
    }

    private fun getMeasuredHeight() : Int {
        if (builder.height != 0) {
            return builder.height
        }
        return this.binding.root.measuredHeight
    }

    private fun initializeBackground() {
        with(binding.tooltipCard) {
            alpha = builder.alpha
            radius = builder.cornerRadius
            ViewCompat.setElevation(this, builder.elevation)
            background = builder.backgroundDrawable ?: GradientDrawable().apply {
                setColor(builder.backgroundColor)
                cornerRadius = builder.cornerRadius
            }
            setPadding(
                builder.paddingLeft,
                builder.paddingTop,
                builder.paddingRight,
                builder.paddingBottom
            )
        }
    }

    private fun initializeTooltipContent() {
        val paddingSize = builder.arrowSize - 1
        val elevation = builder.elevation.toInt()
        with(binding.tooltipContent) {
            when (builder.tooltipOrientation) {
                TooltipOrientation.START -> setPadding(paddingSize, elevation, paddingSize, elevation)
                TooltipOrientation.END -> setPadding(paddingSize, elevation, paddingSize, elevation)
                TooltipOrientation.TOP -> setPadding(elevation, paddingSize, elevation, paddingSize.coerceAtLeast(elevation))
                TooltipOrientation.BOTTOM -> setPadding(elevation, paddingSize, elevation, paddingSize.coerceAtLeast(elevation))
            }
        }
    }

    private fun initializeText() {
        with(binding.tooltipText) {
            text = builder.text
        }
    }

    private fun canShowTooltipWindow(anchor: View): Boolean {
        return !isShowing && !destroyed && !context.isFinishing && bodyWindow.contentView.parent == null && ViewCompat.isAttachedToWindow(
            anchor
        )
    }

    private inline fun show(vararg anchor: View, crossinline block: () -> Unit) {
        val mainAnchor = anchor[0]
        if (canShowTooltipWindow(mainAnchor)) {
            mainAnchor.post {
                canShowTooltipWindow(mainAnchor).takeIf { it } ?: return@post

                this.isShowing = true

                val dismissDelay = this.builder.autoDismissDuration
                if (dismissDelay != -1L) {
                    dismissWithDelay(dismissDelay)
                }

                this.binding.root.measure(
                    View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED
                )
                this.bodyWindow.width = getMeasureWidth()
                this.bodyWindow.height = getMeasuredHeight()
                this.binding.tooltipText.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                initializeArrow(mainAnchor)
                initializeTooltipContent()

                block()
            }
        }
    }

    @JvmOverloads
    fun showAtCenter(
        anchor: View,
        xOff: Int = 0,
        yOff: Int = 0,
        centerAlign : TooltipCenterAlign = TooltipCenterAlign.TOP
    ) {
        val halfAnchorWidth = (anchor.measuredWidth * 0.5F).roundToInt()
        val halfAnchorHeight = (anchor.measuredHeight * 0.5F).roundToInt()
        val halfTooltipWidth = (getMeasureWidth() * 0.5F).roundToInt()
        val halfTooltipHeight = (getMeasuredHeight() * 0.5F).roundToInt()
        val rtlAlign = centerAlign.getRTLSupportAlign(builder.isRtlLayout)
        show(anchor) {
            when (rtlAlign) {
                TooltipCenterAlign.TOP -> bodyWindow.showAsDropDown(
                    anchor,
                    builder.supportRtlLayoutFactor * (halfAnchorWidth - halfTooltipWidth + xOff),
                    -(getMeasuredHeight() + halfAnchorHeight) + yOff
                )
                TooltipCenterAlign.BOTTOM -> bodyWindow.showAsDropDown(
                    anchor,
                    builder.supportRtlLayoutFactor * (halfAnchorWidth - halfTooltipWidth + xOff),
                    -halfTooltipHeight + halfAnchorWidth + yOff

                )
                TooltipCenterAlign.START -> bodyWindow.showAsDropDown(
                    anchor,
                    builder.supportRtlLayoutFactor * (halfAnchorWidth - getMeasureWidth() + xOff),
                    (-getMeasuredHeight() + halfAnchorHeight) + yOff
                )
                TooltipCenterAlign.END -> bodyWindow.showAsDropDown(
                    anchor,
                    builder.supportRtlLayoutFactor * (halfAnchorWidth + getMeasureWidth() + xOff),
                    (-getMeasuredHeight() + halfAnchorHeight) + yOff
                )
            }
        }
    }

    @TooltipInlineDsl
    class Builder(private val context: Context) {

        @Px
        @set:JvmSynthetic
        var width: Int = 0

        @Px
        @set:JvmSynthetic
        var minWidth: Int = 0

        @Px
        @set:JvmSynthetic
        var maxWidth: Int = displaySize.x

        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var widthRatio: Float = 0F

        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var minWidthRatio: Float = 0F

        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var maxWidthRatio: Float = 0F

        @Px
        @set:JvmSynthetic
        var height: Int = 0

        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var alpha: Float = 1F

        @set:JvmSynthetic
        var arrowAlignAnchorPadding: Int = 0

        @set:JvmSynthetic
        var arrowAlignAnchorPaddingRatio: Float = 2.5F

        @Px
        @set:JvmSynthetic
        var arrowSize: Int = 12.dp

        val arrowHalfSize: Float
            @JvmSynthetic @Px
            inline get() = arrowSize * 0.5F

        @FloatRange(from = 0.0, to = 1.0)
        @set:JvmSynthetic
        var arrowPosition: Float = 0.5F

        @set:JvmSynthetic
        var arrowLeftPadding: Int = 0

        @set:JvmSynthetic
        var arrowRightPadding: Int = 0

        @set:JvmSynthetic
        var arrowTopPadding: Int = 0

        @set:JvmSynthetic
        var arrowBottomPadding: Int = 0

        @set:JvmSynthetic
        var arrowElevation: Float = 0F

        @set:JvmSynthetic
        var autoDismissDuration: Long = -1L

        @ColorInt
        @set:JvmSynthetic
        var backgroundColor: Int = R.color.shades5

        @set:JvmSynthetic
        var backgroundDrawable: Drawable? = null

        @Px
        @set:JvmSynthetic
        var cornerRadius: Float = 8F.dp

        @set:JvmSynthetic
        var elevation: Float = 2F.dp

        @set:JvmSynthetic
        var isRtlLayout: Boolean = context.resources.configuration.layoutDirection == LayoutDirection.RTL

        @set:JvmSynthetic
        var isVisibleArrow: Boolean = true

        @Px
        @set:JvmSynthetic
        var paddingLeft: Int = 16

        @Px
        @set:JvmSynthetic
        var paddingTop: Int = 8

        @Px
        @set:JvmSynthetic
        var paddingRight: Int = 16

        @Px
        @set:JvmSynthetic
        var paddingBottom: Int = 8

        @set:JvmSynthetic
        var supportRtlLayoutFactor: Int = LTR.unaryMinus(isRtlLayout)

        @set:JvmSynthetic
        var text: CharSequence = ""

        @set:JvmSynthetic
        var tooltipOrientation: TooltipOrientation = TooltipOrientation.BOTTOM

        fun build(): LPTooltip = LPTooltip(
            context = context,
            builder = this@Builder
        )

        fun setText(value: CharSequence): Builder = apply { this.text  = value}
    }

    fun dismiss() {
        if (this.isShowing) {
            val dismissWindow: () -> Unit = {
                this.isShowing = false
                this.bodyWindow.dismiss()
                this.handler.removeCallbacks(autoDismissRunnable)
            }
            dismissWindow()
        }
    }

    fun dismissWithDelay(delay: Long): Boolean = handler.postDelayed(autoDismissRunnable, delay)

}
