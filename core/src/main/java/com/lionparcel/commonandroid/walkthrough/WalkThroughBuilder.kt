package com.lionparcel.commonandroid.walkthrough

import android.app.Activity
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.DialogFragment
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.walkthrough.utils.toDp
import java.lang.ref.WeakReference

class WalkThroughBuilder(activity: Activity) {

    internal var dialogFragmentReference: WeakReference<DialogFragment>? = null
    internal var activityReference: WeakReference<Activity> = WeakReference(activity)
    internal var offsetPosition: IntArray = intArrayOf(0,0,0,0)
    internal var tittle: String? = null
    internal var finishRes: Int = R.string.general_button_finish
    internal var nextRes: Int = R.string.general_button_next
    internal var forceBackButtonVisibility: Boolean? = null
    internal var disableSkipButton: Boolean = false
    internal var description: String? = null
    internal var descriptionSpannableString: SpannableStringBuilder? = null
    internal var sequencePosition: WalkThrough.SequencePosition? = null
    internal val arrowPositionList = ArrayList<WalkThrough.ArrowPosition>()
    internal var targetView: WeakReference<View>? = null
    internal var targetArrowView: WeakReference<View>? = null
    internal var walkThroughMessageListener: WalkThroughMessageViewListener? = null
    internal var walkThroughSequenceListener: WalkThroughSequenceListener? = null
    internal var showIndex: Boolean = true
    internal var walkThroughSequenceIndex: Int = 1
    internal var walkThroughSequenceTotal: Int = 1
    internal var skipListener: WalkThroughSkipListener? = null
    internal var finishListener: WalkThroughFinishListener? = null
    internal var beatingAnimation: Boolean = true
    internal var dim: Boolean = true
    internal var autoNext: Boolean = false
    internal var customRoundedCorner: Int = 4.toDp()

    private var onGlobalLayoutListenerTargetView: ViewTreeObserver.OnGlobalLayoutListener? = null

    fun title(tittle: String): WalkThroughBuilder {
        this.tittle = tittle
        return this
    }

    /**
     * default = true
     */
    fun showIndex(showIndex: Boolean): WalkThroughBuilder {
        this.showIndex = showIndex
        return this
    }

    fun autoNext(autoNext: Boolean): WalkThroughBuilder {
        this.autoNext = autoNext
        return this
    }

    fun customRoundedCorner(customRoundedCorner: Int): WalkThroughBuilder {
        this.customRoundedCorner = customRoundedCorner
        return this
    }

    fun offsetPosition(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
    ): WalkThroughBuilder {
        this.offsetPosition = intArrayOf(left, top, right, bottom)
        return this
    }

    fun description(subtitle: String?): WalkThroughBuilder {
        this.description = subtitle
        return this
    }

    fun descriptionSpannableString(descriptionSpannableString: SpannableStringBuilder?): WalkThroughBuilder {
        this.descriptionSpannableString = descriptionSpannableString
        return this
    }

    /**
     * Don't forgot set backgroundColor, Otherwise it will be transparent
     */
    fun targetView(targetView: View): WalkThroughBuilder {
        this.targetView = WeakReference(targetView)
        return this
    }

    fun targetArrowView(targetArrowView: View): WalkThroughBuilder {
        this.targetArrowView = WeakReference(targetArrowView)
        return this
    }

    fun walkThroughSequenceIndex(index: Int, total: Int): WalkThroughBuilder {
        this.walkThroughSequenceIndex = index
        this.walkThroughSequenceTotal = total
        return this
    }

    fun skipListener(skipListener: WalkThroughSkipListener?): WalkThroughBuilder {
        this.skipListener = skipListener
        return this
    }

    fun finishListener(finishListener: WalkThroughFinishListener?): WalkThroughBuilder {
        this.finishListener = finishListener
        return this
    }

    fun contentListener(listener: WalkThroughMessageViewListener): WalkThroughBuilder {
        this.walkThroughMessageListener = listener
        return this
    }

    fun sequenceListener(listener: WalkThroughSequenceListener): WalkThroughBuilder {
        this.walkThroughSequenceListener = listener
        return this
    }

    fun onDialogFragment(dialogFragment: DialogFragment): WalkThroughBuilder {
        this.dialogFragmentReference = WeakReference(dialogFragment)
        return this
    }

    fun disableDim(): WalkThroughBuilder {
        this.dim = false
        return this
    }

    protected open fun build(): WalkThrough {
        return WalkThrough(this)
    }

    fun show(): WalkThrough {
        if (this.sequencePosition == null)
            this.sequencePosition = WalkThrough.SequencePosition.FIRST

        val walkThrough = build()

        if (targetView != null) {
            val targetView = targetView?.get()
            if (targetView?.height == 0 || targetView?.width == 0) {
                onGlobalLayoutListenerTargetView = ViewTreeObserver.OnGlobalLayoutListener {
                    walkThrough.show()
                    targetView.viewTreeObserver?.removeOnGlobalLayoutListener(
                        onGlobalLayoutListenerTargetView
                    )
                }
                targetView.viewTreeObserver?.addOnGlobalLayoutListener(
                    onGlobalLayoutListenerTargetView
                )
            } else {
                walkThrough.show()
            }
        } else {
            walkThrough.show()
        }
        return walkThrough
    }
}
