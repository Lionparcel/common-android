package com.lionparcel.commonandroid.loading

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPCustomRefreshSpinner @JvmOverloads constructor(
    mContext: Context? = null,
    attrs: AttributeSet? = null
) : ProgressBar(mContext) {

    init {
        with(context.obtainStyledAttributes(attrs, R.styleable.CustomRefreshSpinner)) {
            getResourceId(R.styleable.CustomRefreshSpinner_drawAbleType, 0)
                .also {
                    try {
                        indeterminateDrawable = ContextCompat.getDrawable(context, it)
                    } catch (e: Exception) {
                        indeterminateDrawable = ContextCompat.getDrawable(context, R.drawable.custom_progressbar)
                    }
                }
            recycle()
        }
    }


}