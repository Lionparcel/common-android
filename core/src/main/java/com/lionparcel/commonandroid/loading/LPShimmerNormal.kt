package com.lionparcel.commonandroid.loading

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPShimmerNormal @JvmOverloads constructor(
    mContext: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(mContext,attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.lp_list_shimmer_normal, this, true)
    }


}