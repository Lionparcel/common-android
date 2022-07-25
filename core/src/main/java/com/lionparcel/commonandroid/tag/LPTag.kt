package com.lionparcel.commonandroid.tag

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R

class LPTag : LinearLayout {

    private val llTagParent : LinearLayout
    private val ivSingleTag : ImageView
    private val tvSingleTag : TextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0 )
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

            } finally {
                recycle()
            }
        }

        llTagParent = findViewById(R.id.llTagParent)
        ivSingleTag = findViewById(R.id.ivSingleTag)
        tvSingleTag = findViewById(R.id.tvSingleTag)

        tvSingleTag.text = "Paket untukmu"
        ivSingleTag.setImageResource(R.drawable.ics_f_check_circle)
        ImageViewCompat.setImageTintList(ivSingleTag, ContextCompat.getColorStateList(context, R.color.shades3))
    }
}