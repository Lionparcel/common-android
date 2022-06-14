package com.lionparcel.commonandroid.header

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class LPHeaderCA : LinearLayout {

    private val llHeader : LinearLayout
    private val llHeaderText : LinearLayout
    private val imgBtnBackHeader : ImageButton
    private val txtHeaderLabel : TextView
    private val imgBtnIcon1 : ImageButton
    private val imgBtnIcon2 : ImageButton

    private var textLabel : String
    private var enableBackButton : Boolean
    private var showFirstIcon : Boolean
    private var showSecondButton : Boolean

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_header_ca, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPHeaderCA,
            0,
            0
        ).apply {
            try {
                textLabel = getString(R.styleable.LPHeaderCA_headerLabel).setString()
                enableBackButton = getBoolean(R.styleable.LPHeaderCA_showBackButton, false)
                showFirstIcon = getBoolean(R.styleable.LPHeaderCA_showFirstIconButton, false)
                showSecondButton = getBoolean(R.styleable.LPHeaderCA_showSecondIconButton, false)

            } finally {
                recycle()
            }
        }

        llHeader = findViewById(R.id.ll_header)
        llHeaderText = findViewById(R.id.ll_header_text)
        imgBtnBackHeader = findViewById(R.id.img_btn_back_header)
        txtHeaderLabel = findViewById(R.id.txt_header_label)
        imgBtnIcon1 = findViewById(R.id.img_btn_info_1)
        imgBtnIcon2 = findViewById(R.id.img_btn_info_2)

        // set label text
        setHeaderLabel(textLabel)
        // show back button
        showBackButton(enableBackButton)
        // show icon
        showIconButton(showFirstIcon, showSecondButton)

    }

    fun setHeaderLabel(textLabel : String){
        txtHeaderLabel.text = textLabel
    }

    fun showBackButton(isEnable : Boolean){
        imgBtnBackHeader.isVisible = isEnable
        if (isEnable){
            val layoutParams = txtHeaderLabel.layoutParams as LayoutParams
            layoutParams.marginStart = 16
            txtHeaderLabel.layoutParams = layoutParams
            imgBtnBackHeader.setOnClickListener {
                val context = context as Activity
                context.finish()
            }
        }
    }

    fun showIconButton(firstButton : Boolean, secondButton : Boolean = false){
        if (firstButton){
            imgBtnIcon1.isVisible = firstButton
        }
        if (secondButton && firstButton){
            imgBtnIcon2.isVisible = secondButton
        }
    }
}