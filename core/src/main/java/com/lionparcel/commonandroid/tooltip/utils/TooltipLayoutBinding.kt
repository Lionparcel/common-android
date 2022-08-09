package com.lionparcel.commonandroid.tooltip.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.modal.inflate

class TooltipLayoutBinding : ViewBinding{

    private lateinit var rootView : FrameLayout

    override fun getRoot(): View {
        return rootView.inflate(R.layout.lp_layout_tooltip_body, false)
    }
}
