package com.lionparcel.commonandroid.listing

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.setRegularFont
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPListing : ConstraintLayout {

    enum class StartIcon(val type : Int){
        NO_ICON(0),
        RADIO_BUTTON(1),
        CHECK_BOX(2),
        CLOSE_START(3),
        ICON_START(4)
    }

    enum class EndIcon(val type : Int){
        NO_ICON(0),
        SWITCH(1),
        CLOSE_END(2),
        ICON_END(3),
        BUTTON_END(4)
    }

    private var listingStyle: Int
    private var listDivider: Boolean
    private var iconStart: Int
    private var iconEnd: Int
    private var buttonText: String
    private var iconStartImage: Drawable
    private var iconEndImage: Drawable
    private var backgroundColorEffect : Boolean

    private val clListingItem : ConstraintLayout
    private val llIconStart : LinearLayout
    private val llThumbnail : LinearLayout
    private val txtTitle : TextView
    private val txtSubtitle : TextView
    private val ivThumbnail : ImageView
    private val ivIconStart : ImageView
    private val ivIconEnd : ImageView
    private val ivCloseStart : ImageView
    private val ivCloseEnd : ImageView
    private val btnEnd : Button
    private val divider : View

    private fun String?.setString() = this ?: ""

    val checkBox : CheckBox
    val radioButton : RadioButton
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    val switchEnd : Switch

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_layout_listing_items, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPListing,
            0,
            0
        ).apply {
            try {
                listingStyle = getInt(R.styleable.LPListing_listStyle, 0)
                listDivider = getBoolean(R.styleable.LPListing_addListDivider, false)
                iconStart = getInt(R.styleable.LPListing_setIconStart, 0)
                iconEnd = getInt(R.styleable.LPListing_setIconEnd, 0)
                buttonText = getString(R.styleable.LPListing_buttonText).setString()
                iconStartImage = getDrawable(R.styleable.LPListing_iconStartImage)
                    ?: resources.getDrawable(R.drawable.ic_f_star)
                iconEndImage = getDrawable(R.styleable.LPListing_iconEndImage)
                    ?: resources.getDrawable(R.drawable.ic_o_chevron_right)
                backgroundColorEffect = getBoolean(R.styleable.LPListing_backgroundColorEffect, true)
            } finally {
                recycle()
            }
        }
        clListingItem = findViewById(R.id.cl_listing_item)
        llIconStart = findViewById(R.id.ll_listing_icon_start)
        llThumbnail = findViewById(R.id.ll_listing_thumbnail)
        txtTitle = findViewById(R.id.txt_listing_title)
        txtSubtitle = findViewById(R.id.txt_listing_sub_title)
        ivThumbnail = findViewById(R.id.iv_thumbnail_listing)
        ivIconStart = findViewById(R.id.iv_icon_listing_start)
        ivIconEnd = findViewById(R.id.iv_icon_listing_end)
        ivCloseStart = findViewById(R.id.iv_close_listing_start)
        ivCloseEnd = findViewById(R.id.iv_close_listing_end)
        btnEnd = findViewById(R.id.btn_listing_end)
        checkBox = findViewById(R.id.cb_listing_start)
        radioButton = findViewById(R.id.rb_listing_start)
        switchEnd = findViewById(R.id.sw_listing_end)
        divider = findViewById(R.id.vw_listing_divider_bottom)

        setListingStyle(listingStyle)
        setIconStartVisibility(iconStart)
        setIconEndVisibility(iconEnd)
        setListOutline(listDivider)
        setListingBackground()
        ivIconStart.setImageDrawable(iconStartImage)
        ivIconEnd.setImageDrawable(iconEndImage)
        btnEnd.text = buttonText
    }
    private fun setListingBackground() {
        if (backgroundColorEffect) {
            radioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.background = ContextCompat.getDrawable(context, R.color.pensive5)
                } else {
                    this.background = ContextCompat.getDrawable(context, R.color.transparent)
                }
            }

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.background = ContextCompat.getDrawable(context, R.color.pensive5)
                } else {
                    this.background = ContextCompat.getDrawable(context, R.color.transparent)
                }
            }

            switchEnd.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.background = ContextCompat.getDrawable(context, R.color.pensive5)
                } else {
                    this.background = ContextCompat.getDrawable(context, R.color.transparent)
                }
            }
        } else {
            this.background = ContextCompat.getDrawable(context, R.color.transparent)
        }
    }

    private fun setListingStyle(listingStyle: Int) {
        when (listingStyle) {
            0 -> {
                val set = ConstraintSet()
                llThumbnail.isVisible = false
                txtSubtitle.isVisible = false
                txtTitle.setRegularFont()
                set.clone(clListingItem)
                set.connect(
                    R.id.txt_listing_title,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
                set.applyTo(clListingItem)
            }
            1 -> {
                llThumbnail.isVisible = false
                txtSubtitle.isVisible = true
            }
            2 -> {
                llThumbnail.isVisible = true
                txtSubtitle.isVisible = true
            }
        }
    }

    private fun setIconStartVisibility(iconStart: Int) {
        when (iconStart) {
            0 -> {
                llIconStart.isVisible = false
            }
            1 -> {
                llIconStart.isVisible = true
                radioButton.isVisible = true
                checkBox.isVisible = false
                ivCloseStart.isVisible = false
                ivIconStart.isVisible = false
                when (listingStyle) {
                    1 -> llIconStart.updateLayoutParams<LayoutParams> {
                        this.bottomToBottom = LayoutParams.UNSET
                    }
                }
            }
            2 -> {
                llIconStart.isVisible = true
                radioButton.isVisible = false
                checkBox.isVisible = true
                ivCloseStart.isVisible = false
                ivIconStart.isVisible = false
                when (listingStyle) {
                    1 -> llIconStart.updateLayoutParams<LayoutParams> {
                        this.bottomToBottom = LayoutParams.UNSET
                    }
                }
            }
            3 -> {
                llIconStart.isVisible = true
                radioButton.isVisible = false
                checkBox.isVisible = false
                ivCloseStart.isVisible = true
                ivIconStart.isVisible = false
            }
            4 -> {
                llIconStart.isVisible = true
                radioButton.isVisible = false
                checkBox.isVisible = false
                ivCloseStart.isVisible = false
                ivIconStart.isVisible = true
            }
        }
    }

    private fun setIconEndVisibility(iconEnd: Int) {
        when (iconEnd) {
            0 -> {
                switchEnd.isVisible = false
                ivCloseEnd.isVisible = false
                ivIconEnd.isVisible = false
                btnEnd.isVisible = false
            }
            1 -> {
                switchEnd.isVisible = true
                ivCloseEnd.isVisible = false
                ivIconEnd.isVisible = false
                btnEnd.isVisible = false
            }
            2 -> {
                switchEnd.isVisible = false
                ivCloseEnd.isVisible = true
                ivIconEnd.isVisible = false
                btnEnd.isVisible = false
            }
            3 -> {
                switchEnd.isVisible = false
                ivCloseEnd.isVisible = false
                ivIconEnd.isVisible = true
                btnEnd.isVisible = false
            }
            4 -> {
                switchEnd.isVisible = false
                ivCloseEnd.isVisible = false
                ivIconEnd.isVisible = false
                btnEnd.isVisible = true
            }
        }
    }

    // PUBLIC METHOD
    fun setListingTitle(title: String) {
        txtTitle.text = title
    }

    fun setListingSubtitle(subtitle: String) {
        txtSubtitle.text = subtitle
    }

    fun setListingThumbnail(thumbnail: Int) {
        ivThumbnail.setImageResource(thumbnail)
    }

    fun setButtonOnClickListener(listener: (View) -> Unit) {
        btnEnd.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun setCloseStartClickListener(listener: (View) -> Unit) {
        ivCloseStart.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun setCloseEndClickListener(listener: (View) -> Unit) {
        ivCloseEnd.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun iconStartVisibility(visibility: StartIcon) {
        setIconStartVisibility(visibility.type)
    }

    fun iconEndVisibility(visibility: EndIcon) {
        setIconEndVisibility(visibility.type)
    }

    fun setListOutline(listDivider: Boolean) {
        divider.isVisible = listDivider
    }

    fun setEnabledView(enable: Boolean) {
        if (!enable) this.alpha = 0.5F
        this.isEnabled = enable
        radioButton.isEnabled = enable
        checkBox.isEnabled = enable
        ivCloseStart.isEnabled = enable
        ivCloseEnd.isEnabled = enable
        switchEnd.isEnabled = enable
        btnEnd.isEnabled = enable
        invalidate()
        requestLayout()
    }
}
