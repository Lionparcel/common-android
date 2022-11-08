package com.lionparcel.commonandroid.walkthrough

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.walkthrough.utils.AnimationUtils
import com.lionparcel.commonandroid.walkthrough.utils.ScreenUtils
import java.lang.ref.WeakReference
import kotlin.math.max

open class WalkThrough(builder: WalkThroughBuilder) {

    companion object {
        const val FOREGROUND_LAYOUT_ID = 731

        private const val DURATION_WALK_THROUGH_ANIMATION = 200
        private const val DURATION_BACKGROUND_ANIMATION = 500
        const val DURATION_BEATING_ANIMATION = 500
    }

    enum class ArrowPosition { TOP, BOTTOM, LEFT, RIGHT }

    enum class SequencePosition { FIRST, MIDDLE, LAST, SINGLE }

    private val activityReference: WeakReference<Activity> = builder.activityReference
    private val dialogFragmentReference: WeakReference<DialogFragment>? =
        builder.dialogFragmentReference

    private val targetViewOffsetPosition = builder.offsetPosition
    private val title = builder.tittle
    private val description = builder.description
    private val descriptionSpannableString = builder.descriptionSpannableString
    private val arrowPositionList = builder.arrowPositionList
    private val targetView = builder.targetView
    private val targetArrowView = builder.targetArrowView
    private val finishRes = builder.finishRes
    private val nextRes = builder.nextRes
    private val forceBackButtonVisibility = builder.forceBackButtonVisibility
    private val disableSkipButton = builder.disableSkipButton
    private val customRoundedCorner = builder.customRoundedCorner

    private val contentListener = builder.walkThroughMessageListener
    private val sequenceListener = builder.walkThroughSequenceListener
    private val skipListener = builder.skipListener
    private val finishListener = builder.finishListener
    private val sequencePosition = builder.sequencePosition
    private val walkThroughSequenceIndex: Int = builder.walkThroughSequenceIndex
    private val walkThroughSequenceTotal: Int = builder.walkThroughSequenceTotal
    private val showIndex: Boolean = builder.showIndex
    private val beatingAnimation: Boolean = builder.beatingAnimation
    private val dim: Boolean = builder.dim

    private val rootView by lazy {
        return@lazy if (dialogFragmentReference != null) {
            dialogFragmentReference.get()?.let { getViewRoot(it) }
        } else {
            activityReference.get()?.let { getViewRoot(it) }
        }
    }
    private var backgroundDimLayout: RelativeLayout? = null
    private var walkThroughMessageViewBuilder: WalkThroughMessageView.Builder? = null

    private val animationView by lazy {
        backgroundDimLayout?.let {
            val animation = AnimationUtils.getFadeInAnimation(
                0,
                DURATION_BACKGROUND_ANIMATION
            )
            AnimationUtils.setAnimationToView(it, animation)
        }
    }

    fun show() {
        when (sequencePosition) {
            SequencePosition.FIRST, SequencePosition.SINGLE -> {
                removeWalkThroughView()
                buildWalkThroughView()
                removeParent(animationView)
                rootView?.addView(animationView)
            }
            else -> buildWalkThroughView()
        }
    }

    private fun removeParent(view: View?) {
        view?.parent?.let {
            (it as ViewGroup).removeView(view)
        }
    }

    private fun buildWalkThroughView() {
        if (targetView != null && arrowPositionList.size <= 1) {
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val activity = activityReference.get() ?: return@postDelayed
                val target = targetView.get() ?: return@postDelayed
                val targetArrow = targetArrowView?.get()
                arrowPositionList.clear()
                if (ScreenUtils.isViewLocatedAtHalfTopOfTheScreen(
                        activity,
                        target,
                        ScreenUtils.getStatusBarHeight(activity)
                    )
                ) {
                    arrowPositionList.add(ArrowPosition.TOP)
                } else {
                    arrowPositionList.add(ArrowPosition.BOTTOM)
                }
                walkThroughMessageViewBuilder = getWalkThroughMessageViewBuilder()
                val targetImageViewId = addTargetViewAtBackgroundDimLayout(
                    target,
                    backgroundDimLayout,
                    targetViewOffsetPosition
                )
                addWalkThroughMessageViewDependingOnTargetView(
                    target,
                    targetArrow,
                    targetImageViewId,
                    walkThroughMessageViewBuilder!!,
                    backgroundDimLayout
                )
            }, DURATION_BACKGROUND_ANIMATION.toLong())
        } else {
            walkThroughMessageViewBuilder = getWalkThroughMessageViewBuilder()
            addWalkThroughMessageViewOnScreenCenter(walkThroughMessageViewBuilder!!, backgroundDimLayout)
        }
        backgroundDimLayout = buildBackgroundDimLayout()
    }

    protected open fun buildBackgroundDimLayout(): RelativeLayout? {
        val activity = activityReference.get() ?: return null
        return if (activity.findViewById<RelativeLayout>(FOREGROUND_LAYOUT_ID) != null) {
            activity.findViewById(FOREGROUND_LAYOUT_ID)
        } else {
            RelativeLayout(activity).apply {
                id = FOREGROUND_LAYOUT_ID
                layoutParams = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                isClickable = true
            }
        }.apply {
            if (dim) setBackgroundColor(ContextCompat.getColor(activity, R.color.black50))
        }
    }

    private fun addWalkThroughMessageViewOnScreenCenter(
        walkThroughMessageViewBuilder: WalkThroughMessageView.Builder,
        backgroundDimLayout: RelativeLayout?
    ) {
        val walkThroughParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        walkThroughParams.addRule(RelativeLayout.CENTER_VERTICAL)
        val walkThroughMessageView = walkThroughMessageViewBuilder.build()
        walkThroughMessageView?.id = createViewId()
        walkThroughMessageView?.let {
            val animation = AnimationUtils.getScaleAnimation(0,
                DURATION_WALK_THROUGH_ANIMATION
            )
            backgroundDimLayout?.addView(
                AnimationUtils.setAnimationToView(it, animation),
                walkThroughParams
            )
        }
    }

    private fun addWalkThroughMessageViewDependingOnTargetView(
        targetView: View,
        targetArrow: View?,
        targetImageViewId: Int?,
        walkThroughMessageViewBuilder: WalkThroughMessageView.Builder,
        backgroundDimLayout: RelativeLayout?
    ) {
        val activity = activityReference.get() ?: return
        val walkThroughParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        when (walkThroughMessageViewBuilder.arrowPosition[0]) {
            ArrowPosition.LEFT -> {
                walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                if (ScreenUtils.isViewLocatedAtHalfTopOfTheScreen(activity, targetView)) {
                    walkThroughParams.setMargins(
                        getXPosition(targetView) + targetView.width,
                        getYPosition(targetView),
                        0,
                        0
                    )
                    walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
                } else {
                    walkThroughParams.setMargins(
                        getXPosition(targetView) + targetView.width,
                        0,
                        0,
                        getScreenHeight(activity) - getYPosition(targetView) - targetView.height
                    )
                    walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                }
            }
            ArrowPosition.RIGHT -> {
                walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                if (ScreenUtils.isViewLocatedAtHalfTopOfTheScreen(activity, targetView)) {
                    walkThroughParams.setMargins(
                        0,
                        getYPosition(targetView),
                        getScreenWidth(activity) - getXPosition(targetView),
                        0
                    )
                    walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
                } else {
                    walkThroughParams.setMargins(
                        0,
                        0,
                        getScreenWidth(activity) - getXPosition(targetView),
                        getScreenHeight(activity) - getYPosition(targetView) - targetView.height
                    )
                    walkThroughParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                }
            }
            ArrowPosition.TOP -> {
                targetImageViewId?.let {
                    walkThroughParams.addRule(RelativeLayout.BELOW, it)
                }
                if (ScreenUtils.isViewLocatedAtHalfLeftOfTheScreen(activity, targetView)) {
                    walkThroughParams.setMargins(
                        0,
                        0,
                        0,
                        0
                    )
                } else {
                    walkThroughParams.setMargins(
                        0,
                        0,
                        0,
                        0
                    )
                }
            }
            ArrowPosition.BOTTOM -> {
                targetImageViewId?.let {
                    walkThroughParams.addRule(RelativeLayout.ALIGN_BOTTOM, it)
                }
                if (ScreenUtils.isViewLocatedAtHalfLeftOfTheScreen(activity, targetView)) {
                    walkThroughParams.setMargins(
                        0,
                        0,
                        0,
                        targetView.height
                    )
                } else {
                    walkThroughParams.setMargins(
                        0,
                        0,
                        0,
                        targetView.height
                    )
                }
            }
        }

        val walkThroughMessageView = walkThroughMessageViewBuilder.targetViewScreenLocation(
            RectF(
                getXPosition(targetView).toFloat(),
                getYPosition(targetView).toFloat(),
                getXPosition(targetView).toFloat() + targetView.width,
                getYPosition(targetView).toFloat() + targetView.height
            )
        )
        targetArrow?.let {
            walkThroughMessageView.targetViewArrowLocation(
                RectF(
                    getXPosition(it).toFloat(),
                    getYPosition(it).toFloat(),
                    getXPosition(it).toFloat() + it.width,
                    getYPosition(it).toFloat() + it.height
                )
            )
        }
        walkThroughMessageView.build()?.let {
            it.id = createViewId()
            it.let {
                val animation = AnimationUtils.getScaleAnimation(0,
                    DURATION_WALK_THROUGH_ANIMATION
                )
                backgroundDimLayout?.addView(
                    AnimationUtils.setAnimationToView(it, animation),
                    walkThroughParams
                )
            }
        }
    }

    private fun addTargetViewAtBackgroundDimLayout(
        targetView: View?,
        backgroundDimLayout: RelativeLayout?,
        targetViewOffsetPosition: IntArray
    ): Int? {
        if (targetView == null) return null
        val activity = activityReference.get() ?: return null

        checkOffsetPosition(targetView, targetViewOffsetPosition)
        val targetScreenshot =
            getRoundedCornerBitmap(takeScreenshot(targetView, targetViewOffsetPosition))

        val targetScreenshotView = ImageView(activity)
        targetScreenshotView.setImageBitmap(targetScreenshot)
        val targetScreenshotViewId = createViewId()
        targetScreenshotView.id = targetScreenshotViewId
        val targetViewParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        val targetViewLeftMargin = getXPosition(targetView) + targetViewOffsetPosition[0]
        val targetViewTopMargin = getYPosition(targetView) + targetViewOffsetPosition[1]
        targetViewParams.setMargins(
            targetViewLeftMargin,
            targetViewTopMargin,
            0,
            0
        )
        backgroundDimLayout?.addView(
            AnimationUtils.setBouncingAnimation(
                targetScreenshotView,
                0,
                DURATION_BEATING_ANIMATION
            ).apply {
                if (!beatingAnimation) visibility = View.INVISIBLE
            },
            targetViewParams
        )

        addShadowLayout(
            backgroundDimLayout,
            targetViewLeftMargin,
            targetViewTopMargin,
            targetScreenshot?.width ?: 0,
            targetScreenshot?.height ?: 0
        )
        return targetScreenshotViewId
    }

    private fun checkOffsetPosition(targetView: View, targetViewOffsetPosition: IntArray) {
        if (getXPosition(targetView) + targetViewOffsetPosition[0] < 0) {
            targetViewOffsetPosition[0] = 0
            targetViewOffsetPosition[2] = 0
        }
        if (getYPosition(targetView) + targetViewOffsetPosition[1] < 0) {
            targetViewOffsetPosition[1] = 0
            targetViewOffsetPosition[3] = 0
        }
    }

    private fun getXPosition(targetView: View): Int {
        val x = ScreenUtils.getAxisXpositionOfViewOnScreen(targetView) - getScreenHorizontalOffset()
        return max(x, 0)
    }

    private fun getYPosition(targetView: View): Int {
        val y = ScreenUtils.getAxisYpositionOfViewOnScreen(targetView) - getScreenVerticalOffset()
        return max(y, 0)
    }

    private fun getScreenVerticalOffset() = if (backgroundDimLayout != null)
        ScreenUtils.getAxisYpositionOfViewOnScreen(backgroundDimLayout!!)
    else 0

    private fun getScreenHorizontalOffset() = if (backgroundDimLayout != null)
        ScreenUtils.getAxisXpositionOfViewOnScreen(backgroundDimLayout!!)
    else 0

    private fun getWalkThroughMessageViewBuilder(): WalkThroughMessageView.Builder {
        return WalkThroughMessageView.Builder()
            .from(activityReference.get()!!)
            .arrowPosition(arrowPositionList)
            .title(title)
            .finishRes(finishRes)
            .nextRes(nextRes)
            .disableSkipButton(disableSkipButton)
            .forceBackButtonVisibility(forceBackButtonVisibility)
            .showIndex(showIndex)
            .walkThroughSequenceIndex(walkThroughSequenceIndex, walkThroughSequenceTotal)
            .description(description)
            .descriptionSpannableString(descriptionSpannableString)
            .sequencePosition(sequencePosition)
            .listener(object : WalkThroughMessageViewListener {
                override fun onBack() {
                    contentListener?.onBack()
                    this@WalkThrough.onBack()
                }

                override fun onNext() {
                    contentListener?.onNext()
                    this@WalkThrough.onNext()
                }

                override fun onFinish() {
                    finishListener?.onFinish()
                    this@WalkThrough.finishSequence()
                }

                override fun onSkip() {
                    skipListener?.onSkip()
                    this@WalkThrough.finishSequence()
                }
            })
    }

    fun onBack() {
        when (sequencePosition) {
            SequencePosition.FIRST, SequencePosition.SINGLE -> finishSequence()
            else -> {
                val isSecondWalkThrough = sequenceListener?.onGetCurrentPosition() == 1
                if (isSecondWalkThrough) {
                    rootView?.removeView(animationView)
                }
                backgroundDimLayout?.removeAllViews()
            }
        }
        sequenceListener?.onPrevWalkThrough()
    }

    fun onNext() {
        if (sequencePosition == SequencePosition.LAST || sequencePosition == SequencePosition.SINGLE) {
            finishSequence()
        } else {
            backgroundDimLayout?.removeAllViews()
        }

        sequenceListener?.onNextWalkThrough()
    }

    fun finishSequence() {
        rootView?.removeView(backgroundDimLayout)
        backgroundDimLayout = null
    }

    private fun removeWalkThroughView(){
        rootView?.removeView(activityReference.get()?.findViewById(FOREGROUND_LAYOUT_ID))
    }

    private fun getViewRoot(activity: Activity): ViewGroup {
        val androidContent = activity.findViewById<ViewGroup>(android.R.id.content)
        return androidContent.parent.parent as ViewGroup
    }

    private fun getViewRoot(dialogFragment: DialogFragment): ViewGroup {
        return dialogFragment.dialog?.window?.decorView as ViewGroup
    }

    protected open fun addShadowLayout(
        backgroundDimLayout: RelativeLayout?,
        leftMargin: Int,
        topMargin: Int,
        width: Int,
        height: Int
    ) {
        //Unit
    }

    /**
     * Convert to rounded corner
     */
    private fun getRoundedCornerBitmap(bitmap: Bitmap?): Bitmap? {
        return bitmap?.run {
            val output: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)
            val color = Color.RED
            val paint = Paint()
            val rect = Rect(0, 0, width, height)
            val rectF = RectF(rect)
            val roundPx = customRoundedCorner
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, roundPx.toFloat(), roundPx.toFloat(), paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(this, rect, rect, paint)
            output
        }
    }

    private fun takeScreenshot(targetView: View, offsetPosition: IntArray) =
        takeScreenshotOfLayoutView(targetView, offsetPosition)

    @Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun takeScreenshotOfLayoutView(targetView: View, offsetPosition: IntArray): Bitmap? {
        if (targetView.width == 0 || targetView.height == 0) {
            return null
        }
        val currentScreenView = rootView?.getChildAt(0)
        currentScreenView?.isDrawingCacheEnabled = true
        currentScreenView?.buildDrawingCache()
        val drawingWidth = currentScreenView?.drawingCache?.width ?: 0
        val drawingHeight = currentScreenView?.drawingCache?.height ?: 0
        val xPosition = getXPosition(targetView).let {
            if (it + targetView.width > drawingWidth) drawingWidth - targetView.width else it
        }
        val yPosition = getYPosition(targetView).let {
            if (it + targetView.height > drawingHeight) drawingHeight - targetView.height else it
        }
        //Create screenshot by x,y
        return currentScreenView?.drawingCache?.let {
            Bitmap.createBitmap(
                it,
                xPosition + offsetPosition[0],
                yPosition + offsetPosition[1],
                targetView.width + offsetPosition[2] * 2,
                targetView.height + offsetPosition[3] * 2
            ).also {
                currentScreenView.isDrawingCacheEnabled = false
                currentScreenView.destroyDrawingCache()
            }
        }
    }

    protected fun createViewId() = View.generateViewId()

    private fun getScreenHeight(context: Context) = ScreenUtils
        .getScreenHeight(context) - getScreenVerticalOffset()

    private fun getScreenWidth(context: Context) =
        ScreenUtils.getScreenWidth(context) - getScreenHorizontalOffset()
}
