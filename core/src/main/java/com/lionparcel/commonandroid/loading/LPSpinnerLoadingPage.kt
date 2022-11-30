package com.lionparcel.commonandroid.loading

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.lionparcel.commonandroid.R

class LPSpinnerLoadingPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val textLoadingSpinner: TextView

    private fun String?.setString() = this?: ""

    init {
        initView()
        textLoadingSpinner = findViewById(R.id.text_loading_title)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPSpinnerLoadingPage,
            0,
            0
        ).apply {
            try {
                textLoadingSpinner.text = getString(R.styleable.LPSpinnerLoadingPage_loadingTitle).setString()
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        inflate(context, R.layout.lp_spinner_loading_page, this)
    }


}