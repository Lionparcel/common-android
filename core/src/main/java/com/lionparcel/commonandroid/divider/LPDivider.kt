package com.lionparcel.commonandroid.divider

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

@Suppress("DEPRECATION")
class LPDivider : ConstraintLayout {

    private var dividerStyle: Int
    private var dividerText: String

    private val vwDividerLine: View
    private val vwDividerBlocLine: View
    private val clDividerCenterText: ConstraintLayout
    private val clDividerStartText : ConstraintLayout
    private val txtDividerCenter: TextView
    private val txtDividerStart : TextView

    private fun String?.setString(): String = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_divider_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPDivider,
            0,
            0
        ).apply {
            try {
                dividerStyle = getInt(R.styleable.LPDivider_dividerStyle, 0)
                dividerText = getString(R.styleable.LPDivider_dividerText).setString()
            } finally {
                recycle()
            }
        }

        vwDividerLine = findViewById(R.id.vw_divider_line)
        vwDividerBlocLine = findViewById(R.id.vw_divider_bloc_line)
        clDividerCenterText = findViewById(R.id.cl_divider_center_text)
        clDividerStartText = findViewById(R.id.cl_divider_start_text)
        txtDividerCenter = findViewById(R.id.txt_divider_center)
        txtDividerStart = findViewById(R.id.txt_divider_start)

        setDividerStyle()
        setDividerText()

    }

    private fun setDividerStyle() {
        when (dividerStyle) {
            0 -> {
                lineDividerStyle(true)
            }
            1 -> {
                blocLineDividerStyle(true)
            }
            2 -> {
                subtitleTextDividerStyle(true)
            }
            3 -> {
                labelTextDividerStyle(true)
            }
            4 -> {
                centerTextDividerStyle(true)
            }
        }
    }

    private fun lineDividerStyle(visible: Boolean) {
        vwDividerLine.isVisible = visible
        vwDividerBlocLine.isVisible = !visible
        clDividerStartText.isVisible = !visible
        clDividerCenterText.isVisible = !visible
    }

    private fun blocLineDividerStyle(visible: Boolean) {
        vwDividerBlocLine.isVisible = visible
        vwDividerLine.isVisible = !visible
        clDividerStartText.isVisible = !visible
        clDividerCenterText.isVisible = !visible
    }

    private fun subtitleTextDividerStyle(visible: Boolean) {
        clDividerStartText.isVisible = visible
        if (visible) {
            val scale = resources.displayMetrics.density
            txtDividerStart.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                resources.getDimension(R.dimen.sp_14) / scale
            )
        }
        vwDividerBlocLine.isVisible = !visible
        vwDividerLine.isVisible = !visible
        clDividerCenterText.isVisible = !visible
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun labelTextDividerStyle(visible: Boolean) {
        clDividerStartText.isVisible = visible
        if (visible) {
            val scale = resources.displayMetrics.density
            txtDividerStart.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                resources.getDimension(R.dimen.sp_12) / scale
            )
            txtDividerStart.background = resources.getDrawable(R.drawable.bg_divider_label_text)
        }
        vwDividerBlocLine.isVisible = !visible
        vwDividerLine.isVisible = !visible
        clDividerCenterText.isVisible = !visible
    }

    private fun centerTextDividerStyle(visible: Boolean) {
        clDividerCenterText.isVisible = visible
        vwDividerBlocLine.isVisible = !visible
        vwDividerLine.isVisible = !visible
        clDividerStartText.isVisible = !visible
    }

    private fun setDividerText(){
        txtDividerCenter.text = dividerText
        txtDividerStart.text = dividerText
    }
}