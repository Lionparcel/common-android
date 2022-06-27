package com.lionparcel.commonandroid.label

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPLabel : LinearLayout {

    private var textTitle : String
    private var labelBackgroundColor : Int
    private var labelBackgroundStyle : Int
    private var enableImage : Boolean
    private var imageIcon : Drawable
    private var labelSize : Int

    private val llLabelParent : LinearLayout
    private val txtLabel : TextView
    private val imgIconLabel : ImageView

    private fun String?.setString() = this?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_label, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPLabel,
            0,
            0
        ).apply {
            try {
                textTitle = getString(R.styleable.LPLabel_textTitle).setString()
                labelBackgroundColor = getInt(R.styleable.LPLabel_labelColor, 0)
                labelBackgroundStyle = getInt(R.styleable.LPLabel_labelBackgroundStyle, 0)
                enableImage = getBoolean(R.styleable.LPLabel_enableImage, false)
                imageIcon = getDrawable(R.styleable.LPLabel_imageIcon)?: resources.getDrawable(R.drawable.ics_f_notification)
                labelSize = getInt(R.styleable.LPLabel_labelSize, 0)
            } finally {
                recycle()
            }
        }

        llLabelParent = findViewById(R.id.ll_label_parent)
        txtLabel = findViewById(R.id.txt_label)
        imgIconLabel = findViewById(R.id.img_icon_label)

        // set text label title
        setTextTitle()
        // set label background
        setBackground()
        // set image visibility
        enableImage()
        // set image drawable
        setImageIcon()
        // set label size
        setLabelSize()
    }
    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun setBackground(){
        when (labelBackgroundColor){
            0 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_onepack)
                when (labelBackgroundStyle){
                    0 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.white))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgIconLabel.imageTintList = resources.getColorStateList(R.color.white)
                        }
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.purple4))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgIconLabel.imageTintList = resources.getColorStateList(R.color.purple4)
                        }
                    }
                }
            }
            1 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_regpack)
                txtLabel.setTextColor(resources.getColor(R.color.blue4))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.blue4)
                }
            }
            2 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_jagopack)
                txtLabel.setTextColor(resources.getColor(R.color.green6))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.green6)
                }
            }
            3 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_docupack)
                txtLabel.setTextColor(resources.getColor(R.color.brown6))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.brown6)
                }
            }
            4 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_sameday)
                txtLabel.setTextColor(resources.getColor(R.color.interpack4))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.interpack4)
                }
            }
            5 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_deliv_order)
                txtLabel.setTextColor(resources.getColor(R.color.aqua5))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.aqua5)
                }
            }
            6 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_bundle)
                txtLabel.setTextColor(resources.getColor(R.color.shades6))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.shades6)
                }
            }
            7 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_big_pack)
                txtLabel.setTextColor(resources.getColor(R.color.orange5))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgIconLabel.imageTintList = resources.getColorStateList(R.color.orange5)
                }
            }
            8 -> {
                llLabelParent.background =
                    resources.getDrawable(R.drawable.bg_selector_label_inactive)
                when (labelBackgroundStyle){
                    0 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.white))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgIconLabel.imageTintList = resources.getColorStateList(R.color.white)
                        }
                    }
                    1 -> {
                        txtLabel.setTextColor(resources.getColor(R.color.shades3))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imgIconLabel.imageTintList = resources.getColorStateList(R.color.shades3)
                        }
                    }
                }
            }
        }
        when (labelBackgroundStyle){
            0 -> llLabelParent.isSelected = false
            1 -> llLabelParent.isSelected = true
        }
    }

    private fun setLabelSize(){
        val scale = resources.displayMetrics.density
        when (labelSize){
            0 -> {
                txtLabel.textSize = 14F
                llLabelParent.setPadding((20 * scale + 0.5F).toInt(), 1, (20 * scale + 0.5F).toInt(), 1)
                imgIconLabel.layoutParams.height = (16 * scale + 0.5F).toInt()
                imgIconLabel.layoutParams.width = (16 * scale + 0.5F).toInt()
                imgIconLabel.requestLayout()
            }
            1 -> {
                txtLabel.textSize = 12F
                llLabelParent.setPadding((16 * scale + 0.5F).toInt(), 1, (16 * scale + 0.5F).toInt(), 1)
                imgIconLabel.layoutParams.height = (12 * scale + 0.5F).toInt()
                imgIconLabel.layoutParams.width = (12 * scale + 0.5F).toInt()
            }
            2 -> {
                txtLabel.textSize = 10F
                llLabelParent.setPadding((12 * scale + 0.5F).toInt(), 1, (12 * scale + 0.5F).toInt(), 1)
                imgIconLabel.layoutParams.height = (12 * scale + 0.5F).toInt()
                imgIconLabel.layoutParams.width = (12 * scale + 0.5F).toInt()
            }
            3 -> {
                txtLabel.textSize = 10F
                llLabelParent.setPadding((4 * scale + 0.5F).toInt(), 1, (4 * scale + 0.5F).toInt(), 1)
                imgIconLabel.layoutParams.height = (12 * scale + 0.5F).toInt()
                imgIconLabel.layoutParams.width = (12 * scale + 0.5F).toInt()
            }
        }
    }

    fun setTextTitle(title : String? = null){
        txtLabel.text = title?: this.textTitle
    }

    fun enableImage(enable : Boolean? = null){
        imgIconLabel.isVisible = enable?: this.enableImage
    }

    fun setImageIcon(image : Int? = null){
        if (image != null){
            imgIconLabel.setImageResource(image)
        } else {
            imgIconLabel.setImageDrawable(this.imageIcon)
        }
    }
}