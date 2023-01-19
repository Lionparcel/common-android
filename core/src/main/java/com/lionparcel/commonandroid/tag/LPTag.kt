package com.lionparcel.commonandroid.tag

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R

class LPTag : LinearLayout {

    private var titleText: String
    private var iconStart: Boolean

    private val llTagParent: LinearLayout
    private val ivSingleTag: ImageView
    private val tvSingleTag: TextView

    private fun String?.setString() : String = this?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_tag_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPTag,
            0,
            0
        ).apply {
            try {
                titleText = getString(R.styleable.LPTag_textTitle).setString()
                iconStart = getBoolean(R.styleable.LPTag_iconStart, false)
            } finally {
                recycle()
            }
        }

        llTagParent = findViewById(R.id.llTagParent)
        ivSingleTag = findViewById(R.id.ivSingleTag)
        tvSingleTag = findViewById(R.id.tvSingleTag)

        tvSingleTag.text = titleText
        ivSingleTag.isVisible = iconStart
        ivSingleTag.setImageResource(R.drawable.ics_f_check_circle)
        ImageViewCompat.setImageTintList(
            ivSingleTag,
            ContextCompat.getColorStateList(context, R.color.shades3)
        )
    }
    private fun setImageColor(color : Int) {
        ImageViewCompat.setImageTintList(
            ivSingleTag,
            ContextCompat.getColorStateList(context, color)
        )
    }

    private fun setTextColor(color: Int) {
        tvSingleTag.setTextColor(ContextCompat.getColorStateList(context, color))
    }

    fun titleText(title : String) {
        tvSingleTag.text = title
    }

    fun setIconVisibility(visible : Boolean) {
        ivSingleTag.isVisible = visible
    }

    fun tagStatus(status: Boolean){
        when (status) {
            false -> {
                this.isSelected = false
                setImageColor(R.color.shades3)
                setTextColor(R.color.shades5)
            }
            true -> {
                this.isSelected = true
                setImageColor(R.color.blue4)
                setTextColor(R.color.blue3)
            }
        }
    }
}
