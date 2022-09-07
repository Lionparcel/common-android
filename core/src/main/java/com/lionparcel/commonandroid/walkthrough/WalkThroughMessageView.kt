package com.lionparcel.commonandroid.walkthrough

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.walkthrough.utils.DensityUtils
import com.lionparcel.commonandroid.walkthrough.utils.ScreenUtils
import com.lionparcel.commonandroid.walkthrough.widget.indicator.PositionIndicatorView
import java.lang.ref.WeakReference
import kotlin.math.roundToInt

class WalkThroughMessageView : ConstraintLayout {

    companion object {
        private const val WIDTH_ARROW = 20f
    }

    private var itemView: View? = null

    private val txtTittle by lazy { findViewById<TextView>(R.id.txtTittle) }
    private val txtDescription by lazy { findViewById<TextView>(R.id.txtDescription) }
    private val indicator by lazy { findViewById<PositionIndicatorView>(R.id.indicator) }
    private val btnBack by lazy { findViewById<ImageButton>(R.id.btnBack) }
    private val btnNext by lazy { findViewById<Button>(R.id.btnNext) }
    private val ivClose by lazy { findViewById<ImageView>(R.id.ivClose) }

    private var targetViewScreenLocation: RectF? = null
    private var targetViewArrowLocation: RectF? = null
    private var viewBackgroundColor = ContextCompat.getColor(context, R.color.white)
    private var arrowPositionList = ArrayList<WalkThrough.ArrowPosition>()

    private var paint: Paint? = null

    class Builder {
        private lateinit var context: WeakReference<Context>
        var targetViewScreenLocation: RectF? = null
        var targetViewArrowLocation: RectF? = null
        var title: String? = null
        var description: String? = null
        var descriptionSpannableString: SpannableStringBuilder? = null
        var arrowPosition = ArrayList<WalkThrough.ArrowPosition>()
        var sequencePosition: WalkThrough.SequencePosition? = null
        var listener: WalkThroughMessageViewListener? = null
        var showIndex: Boolean = true
        var walkThroughSequenceIndex: Int = 1
        var walkThroughSequenceTotal: Int = 1
        var finishRes: Int = R.string.general_button_finish
        var nextRes: Int = R.string.general_button_next
        var disableSkipButton: Boolean = false
        var forceBackButtonVisibility: Boolean? = null

        fun from(context: Context): Builder {
            this.context = WeakReference(context)
            return this
        }

        fun title(title: String?): Builder {
            this.title = title
            return this
        }

        fun showIndex(showIndex: Boolean): Builder {
            this.showIndex = showIndex
            return this
        }

        fun walkThroughSequenceIndex(index: Int, total: Int): Builder {
            this.walkThroughSequenceIndex = index
            this.walkThroughSequenceTotal = total
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun descriptionSpannableString(descriptionSpannableString: SpannableStringBuilder?): Builder {
            this.descriptionSpannableString = descriptionSpannableString
            return this
        }

        fun targetViewScreenLocation(targetViewLocationOnScreen: RectF): Builder {
            this.targetViewScreenLocation = targetViewLocationOnScreen
            return this
        }

        fun targetViewArrowLocation(targetViewArrowOnScreen: RectF): Builder {
            this.targetViewArrowLocation = targetViewArrowOnScreen
            return this
        }

        fun arrowPosition(arrowPosition: List<WalkThrough.ArrowPosition>): Builder {
            this.arrowPosition.clear()
            this.arrowPosition.addAll(arrowPosition)
            return this
        }

        fun sequencePosition(sequencePosition: WalkThrough.SequencePosition?): Builder {
            this.sequencePosition = sequencePosition
            return this
        }

        fun listener(listener: WalkThroughMessageViewListener?): Builder {
            this.listener = listener
            return this
        }

        fun finishRes(@StringRes res: Int): Builder {
            this.finishRes = res
            return this
        }

        fun nextRes(@StringRes res: Int): Builder {
            this.nextRes = res
            return this
        }

        fun disableSkipButton(disable: Boolean): Builder {
            this.disableSkipButton = disable
            return this
        }

        fun forceBackButtonVisibility(state: Boolean?): Builder {
            this.forceBackButtonVisibility = state
            return this
        }

        fun build() = context.get()?.let { WalkThroughMessageView(it, this) }
    }

    constructor(context: Context): super(context, null) {
        initView()
    }

    constructor(context: Context, builder: Builder) : super(context, null) {
        initView()
        setAttributes(builder)
        setWalkThroughMessageListener(builder)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.WalkThroughMessageViewStyle) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int)
            : super(ContextThemeWrapper(context, R.style.WalkThroughMessageViewTheme), attrs, defStyleAttr)

    private fun initView() {
        setWillNotDraw(false)
        inflateXML()
    }

    private fun inflateXML() {
        itemView = inflate(context, R.layout.walkthrough_message_dialog, this)
    }

    private fun setAttributes(builder: Builder) {
        val isLastIndex = builder.walkThroughSequenceIndex.plus(1) == builder.walkThroughSequenceTotal
        builder.title?.let {
            txtTittle?.isVisible = true
            txtTittle?.text = it
        }
        btnNext.text = context.getString(
            if (isLastIndex) builder.finishRes
            else builder.nextRes
        )
        if (builder.walkThroughSequenceTotal != 0 && builder.showIndex) {
            indicator.setQuantity(builder.walkThroughSequenceTotal)
            indicator.setActivePosition(builder.walkThroughSequenceIndex)
        }
        builder.description?.let {
            txtDescription?.isVisible = true
            txtDescription?.text = it
        } ?: builder.descriptionSpannableString?.let {
            txtDescription?.isVisible = true
            txtDescription?.text = it
        }
        arrowPositionList = builder.arrowPosition
        targetViewScreenLocation = builder.targetViewScreenLocation
        targetViewArrowLocation = builder.targetViewArrowLocation
        btnBack.isVisible = builder.forceBackButtonVisibility ?: (builder.walkThroughSequenceTotal > 1 && builder.walkThroughSequenceIndex > 0)
    }

    private fun setWalkThroughMessageListener(builder: Builder) {
        builder.listener?.let { listener ->
            btnBack.setOnClickListener { listener.onBack() }
            btnNext.setOnClickListener {
                if (builder.walkThroughSequenceIndex.plus(1) == builder.walkThroughSequenceTotal) {
                    listener.onFinish()
                } else {
                    listener.onNext()
                }
            }
            ivClose.setOnClickListener { listener.onSkip() }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        prepareToDraw()
        drawRectangle(
            canvas,
            cornersDependsOnArrowPosition(arrowPositionList, targetViewScreenLocation)
        )

        for (arrowPosition in arrowPositionList) {
            drawArrow(
                canvas,
                arrowPosition,
                if (targetViewArrowLocation != null)
                    targetViewArrowLocation
                else
                    targetViewScreenLocation
            )
        }
    }

    private fun drawArrow(
        canvas: Canvas,
        arrowPosition: WalkThrough.ArrowPosition,
        targetViewLocationOnScreen: RectF?
    ) {
        val xPosition: Int?
        val yPosition: Int?

        when (arrowPosition) {
            WalkThrough.ArrowPosition.LEFT -> {
                xPosition = getMargin()
                yPosition = if (targetViewLocationOnScreen != null)
                    getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen)
                else height / 2
            }
            WalkThrough.ArrowPosition.RIGHT -> {
                xPosition = getViewWidth() - getMargin()
                yPosition = if (targetViewLocationOnScreen != null)
                    getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen)
                else height / 2
            }
            WalkThrough.ArrowPosition.TOP -> {
                xPosition = if (targetViewLocationOnScreen != null)
                    getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen)
                else width / 2
                yPosition = getMargin()
            }
            WalkThrough.ArrowPosition.BOTTOM -> {
                xPosition = if (targetViewLocationOnScreen != null)
                    getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen)
                else width / 2
                yPosition = height - getMargin()
            }
        }

        drawRhombus(
            canvas,
            paint,
            xPosition,
            yPosition,
            DensityUtils.convertDpToPixel(WIDTH_ARROW, context)
        )
    }

    private fun drawRhombus(
        canvas: Canvas,
        paint: Paint?,
        x: Int,
        y: Int,
        width: Int
    ) {
        val halfRhombusWidth = width / 2

        val path = Path()
        path.moveTo(x.toFloat(), (y + halfRhombusWidth).toFloat())
        path.lineTo((x - halfRhombusWidth).toFloat(), y.toFloat())
        path.lineTo(x.toFloat(), (y - halfRhombusWidth).toFloat())
        path.lineTo((x + halfRhombusWidth).toFloat(), y.toFloat())
        path.lineTo(x.toFloat(), (y + halfRhombusWidth).toFloat())
        path.close()

        paint?.let { canvas.drawPath(path, it) }
    }

    private fun getArrowHorizontalPositionDependingOnTarget(targetViewLocationOnScreen: RectF) =
        when {
            isOutOfRightBound(targetViewLocationOnScreen) -> width - getSecurityArrowMargin()
            isOutOfLeftBound(targetViewLocationOnScreen) == true -> getSecurityArrowMargin()
            else -> targetViewLocationOnScreen.let {
                (it.centerX() - ScreenUtils.getAxisXpositionOfViewOnScreen(this)).roundToInt()
            }
        }

    private fun getArrowVerticalPositionDependingOnTarget(targetViewLocationOnScreen: RectF) =
        when {
            isOutOfBottomBound(targetViewLocationOnScreen) -> height - getSecurityArrowMargin()
            isOutOfTopBound(targetViewLocationOnScreen) == true -> getSecurityArrowMargin()
            else -> targetViewLocationOnScreen.let {
                (it.centerY() + ScreenUtils.getStatusBarHeight(context) - ScreenUtils.getAxisYpositionOfViewOnScreen(
                    this
                )).roundToInt()
            }
        }

    private fun drawRectangle(canvas: Canvas, corners: FloatArray) {
        val rect = RectF(
            getMargin().toFloat(),
            getMargin().toFloat(),
            getViewWidth() - getMargin().toFloat(),
            height - getMargin().toFloat()
        )
        val path = Path()
        path.addRoundRect(rect, corners, Path.Direction.CW)
        paint?.let { canvas.drawPath(path, it) }
    }

    private fun getViewWidth() = width

    private fun prepareToDraw() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.color = viewBackgroundColor
        paint?.style = Paint.Style.FILL
        paint?.strokeWidth = DensityUtils.convertDpToPixel(2f, context).toFloat()
    }

    private fun cornersDependsOnArrowPosition(
        arrowPosition: List<WalkThrough.ArrowPosition>,
        targetViewLocationOnScreen: RectF?
    ): FloatArray {
        val dp10: Float = DensityUtils.convertDpToPixel(10f, context).toFloat()
        var xLeftTop = dp10
        var xRightTop = dp10
        var yLeftTop = dp10
        var yRightTop = dp10

        var xLeftBottom = dp10
        var xRightBottom = dp10
        var yLeftBottom = dp10
        var yRightBottom = dp10

        if (targetViewLocationOnScreen != null) {
            for (arrow in arrowPosition) {
                when (arrow) {
                    WalkThrough.ArrowPosition.LEFT -> {
                        if (isOutOfBottomBound(targetViewLocationOnScreen)) {
                            yLeftBottom = targetViewLocationOnScreen.left
                            xLeftBottom = targetViewLocationOnScreen.bottom
                        }

                        if (isOutOfTopBound(targetViewLocationOnScreen) == true) {
                            yLeftTop = targetViewLocationOnScreen.left
                            xLeftTop = targetViewLocationOnScreen.top
                        }
                    }
                    WalkThrough.ArrowPosition.RIGHT -> {
                        if (isOutOfBottomBound(targetViewLocationOnScreen)) {
                            yRightBottom = targetViewLocationOnScreen.right
                            xRightBottom = targetViewLocationOnScreen.bottom
                        }

                        if (isOutOfTopBound(targetViewLocationOnScreen) == true) {
                            yRightTop = targetViewLocationOnScreen.right
                            xRightTop = targetViewLocationOnScreen.top
                        }
                    }
                    WalkThrough.ArrowPosition.TOP -> {
                        if (isOutOfLeftBound(targetViewLocationOnScreen) == true) {
                            xLeftTop = targetViewLocationOnScreen.top
                            yLeftTop = targetViewLocationOnScreen.left
                        }
                        if (isOutOfRightBound(targetViewLocationOnScreen)) {
                            xRightTop = targetViewLocationOnScreen.top
                            yRightTop = targetViewLocationOnScreen.right
                        }
                    }
                    WalkThrough.ArrowPosition.BOTTOM -> {
                        if (isOutOfLeftBound(targetViewLocationOnScreen) == true) {
                            xLeftBottom = targetViewLocationOnScreen.bottom
                            yLeftBottom = targetViewLocationOnScreen.left
                        }
                        if (isOutOfRightBound(targetViewLocationOnScreen)) {
                            xRightBottom = targetViewLocationOnScreen.bottom
                            yRightBottom = targetViewLocationOnScreen.right
                        }
                    }
                }
            }
        }

        return floatArrayOf(
            xLeftTop, yLeftTop,   // Top left radius in px
            xRightTop, yRightTop,   // Top right radius in px
            xRightBottom, yRightBottom,   // Bottom right radius in px
            xLeftBottom, yLeftBottom    // Bottom left radius in px
        )
    }

    private fun isOutOfBottomBound(targetViewLocationOnScreen: RectF) =
        targetViewLocationOnScreen.centerY() > ScreenUtils.getAxisYpositionOfViewOnScreen(this) + height - getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(context)

    private fun isOutOfRightBound(targetViewLocationOnScreen: RectF) =
        targetViewLocationOnScreen.centerX() > ScreenUtils.getAxisXpositionOfViewOnScreen(this) + width - getSecurityArrowMargin()

    private fun isOutOfLeftBound(targetViewLocationOnScreen: RectF?) =
        targetViewLocationOnScreen?.let {
            it.centerX() < ScreenUtils.getAxisXpositionOfViewOnScreen(this) + getSecurityArrowMargin()
        }

    private fun isOutOfTopBound(targetViewLocationOnScreen: RectF?) =
        targetViewLocationOnScreen?.let {
            it.centerY() < ScreenUtils.getAxisYpositionOfViewOnScreen(this) + getSecurityArrowMargin() - ScreenUtils.getStatusBarHeight(
                context
            )
        }

    private fun getSecurityArrowMargin() = getMargin() + DensityUtils.convertDpToPixel(
        WIDTH_ARROW / 2, context
    )

    private fun getMargin() = context.resources.getDimensionPixelSize(R.dimen.walkthrough_message_margin)


}
