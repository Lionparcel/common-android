package com.lionparcel.commonandroid.tooltip

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleObserver
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.databinding.LpLayoutTooltipBodyBinding
import com.lionparcel.commonandroid.tooltip.utils.dp
import com.lionparcel.commonandroid.tooltip.utils.isFinishing

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

    init {
        createByBuilder()
    }

    private fun createByBuilder() {
        initializeBackground()

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

                this.builder.pre
            }
        }
    }

    @TooltipInlineDsl
    class Builder(private val context: Context) {

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

        fun build(): LPTooltip = LPTooltip(
            context = context,
            builder = this@Builder
        )
    }

}
