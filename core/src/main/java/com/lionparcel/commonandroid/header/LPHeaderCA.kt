package com.lionparcel.commonandroid.header

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.LPAutoCompleteTextView
import com.lionparcel.commonandroid.form.utils.AutoCompleteArrayAdapter

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPHeaderCA : ConstraintLayout {

    companion object STYLE {
        const val BASIC = 0
        const val WITH_SEARCH = 1
        const val SEARCH_ONLY = 2
    }

    private var textLabel: String
    private var textLabelColor: Int
    private var enableBackButton: Boolean
    private var backButtonImage: Drawable
    private var backButtonColor : Int
    private var showFirstIcon: Boolean
    private var firstIconImage: Drawable
    private var showSecondButton: Boolean
    private var secondIconImage: Drawable
    private var showThirdButton: Boolean
    private var thirdIconImage: Drawable
    private var addElevation: Boolean
    private var headerStyle: Int
    private var searchHint: String
    private var enableTextButton: Boolean
    private var textButtonText: String
    private var enableScanImage: Boolean
    private var imgScanImage: Drawable
    private var enableAssistiveText: Boolean
    private var assistiveText: String
    private var enableErrorText: Boolean
    private var errorText: String

    private val clHeader: ConstraintLayout
    private val imgBtnBackHeader: ImageButton
    private val txtHeaderLabel: TextView
    private val imgBtnIcon1: ImageButton
    private val imgBtnIcon2: ImageButton
    private val imgBtnIcon3: ImageButton
    private val clHeaderSearchEditText: ConstraintLayout
    private val txtSearchAutoComplete: LPAutoCompleteTextView
    private val txtButtonHeader: TextView
    private val imgBtnScanHeader: ImageButton
    private val txtAssistiveHeader: TextView
    private val txtErrorHeader: TextView

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

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
                textLabelColor = getInt(R.styleable.LPHeaderCA_textLabelColor, 0)
                enableBackButton = getBoolean(R.styleable.LPHeaderCA_showBackButton, false)
                backButtonImage = getDrawable(R.styleable.LPHeaderCA_backButtonImage) ?: resources.getDrawable(R.drawable.ic_o_arrow_left_alt)
                backButtonColor = getInt(R.styleable.LPHeaderCA_backButtonColor, 0)
                showFirstIcon = getBoolean(R.styleable.LPHeaderCA_showFirstIconButton, false)
                firstIconImage = getDrawable(R.styleable.LPHeaderCA_firstIconImage) ?: resources.getDrawable(R.drawable.ics_o_info_circle)
                showSecondButton = getBoolean(R.styleable.LPHeaderCA_showSecondIconButton, false)
                secondIconImage = getDrawable(R.styleable.LPHeaderCA_secondIconImage) ?: resources.getDrawable(R.drawable.ics_o_info_circle)
                showThirdButton = getBoolean(R.styleable.LPHeaderCA_showThirdIconButton, false)
                thirdIconImage = getDrawable(R.styleable.LPHeaderCA_thirdIconImage) ?: resources.getDrawable(R.drawable.ics_o_info_circle)
                addElevation = getBoolean(R.styleable.LPHeaderCA_addElevation, false)
                headerStyle = getInt(R.styleable.LPHeaderCA_headerStyle, 0)
                searchHint = getString(R.styleable.LPHeaderCA_searchHint).setString()
                enableTextButton = getBoolean(R.styleable.LPHeaderCA_enableTextButton, false)
                textButtonText = getString(R.styleable.LPHeaderCA_txtButtonText).setString()
                enableScanImage = getBoolean(R.styleable.LPHeaderCA_enableScanImage, false)
                imgScanImage = getDrawable(R.styleable.LPHeaderCA_imageScan) ?: resources.getDrawable(R.drawable.ics_o_scan)
                enableAssistiveText = getBoolean(R.styleable.LPHeaderCA_enableAssistiveText, false)
                assistiveText = getString(R.styleable.LPHeaderCA_assistiveText).setString()
                enableErrorText = getBoolean(R.styleable.LPHeaderCA_enableErrorText, false)
                errorText = getString(R.styleable.LPHeaderCA_errorTextHeader).setString()

            } finally {
                recycle()
            }
        }

        clHeader = findViewById(R.id.cl_header)
        imgBtnBackHeader = findViewById(R.id.img_btn_back_header)
        txtHeaderLabel = findViewById(R.id.txt_header_label)
        imgBtnIcon1 = findViewById(R.id.img_btn_info_1)
        imgBtnIcon2 = findViewById(R.id.img_btn_info_2)
        imgBtnIcon3 = findViewById(R.id.img_btn_info_3)
        clHeaderSearchEditText = findViewById(R.id.cl_header_search_edit_text)
        txtSearchAutoComplete = findViewById(R.id.txt_search_autocomplete)
        txtButtonHeader = findViewById(R.id.txt_btn_header)
        imgBtnScanHeader = findViewById(R.id.img_btn_scan_header)
        txtAssistiveHeader = findViewById(R.id.txt_assistive_header)
        txtErrorHeader = findViewById(R.id.txt_error_header)

        // set header style
        setHeaderStyle()
        // set label text
        setHeaderLabel()
        // set label text color
        setHeaderTextColor()
        // show back button
        setBackButton()
        // set back button color
        setBackButtonColor()
        // show icon
        setIconButton()
        // add shadow
        addElevation()

        txtSearchAutoComplete.handleOnClearIconClick()
        setSearchHint()
        // set text button label
        setTextButton()
        // set image scan
        setImageScan()
        // set assistive text
        setAssistiveText()
        //set error text
        setErrorText()

    }

    private fun setHeaderStyle(
        headerStyle : Int? = null,
        searchImage: Int? = null
    ) {
        val set = ConstraintSet()
        val scale = resources.displayMetrics.density
        when (headerStyle?: this.headerStyle) {
            // Text (Basic)
            0 -> {
                clHeaderSearchEditText.isVisible = false
                txtHeaderLabel.isVisible = true
                if (this.enableBackButton) {
                    set.clone(clHeader)
                    set.connect(
                        R.id.txt_header_label,
                        ConstraintSet.START,
                        R.id.img_btn_back_header,
                        ConstraintSet.END,
                        (16 * scale + 0.5F).toInt()
                    )
                    set.applyTo(clHeader)
                } else {
                    set.clone(clHeader)
                    set.connect(
                        R.id.txt_header_label,
                        ConstraintSet.START,
                        R.id.cl_header,
                        ConstraintSet.START,
                        (24 * scale + 0.5F).toInt()
                    )
                    set.applyTo(clHeader)
                }
                set.clone(clHeader)
                set.connect(
                    R.id.txt_header_label,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    (16 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.txt_header_label,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    (16 * scale + 0.5F).toInt()
                )
                set.applyTo(clHeader)
            }
            // With Search
            1 -> {
                clHeaderSearchEditText.isVisible = true
                txtHeaderLabel.isVisible = true
                if (this.enableBackButton) {
                    set.clone(clHeader)
                    set.connect(
                        R.id.txt_header_label,
                        ConstraintSet.START,
                        R.id.img_btn_back_header,
                        ConstraintSet.END,
                        (16 * scale + 0.5F).toInt()
                    )
                    set.applyTo(clHeader)
                } else {
                    set.clone(clHeader)
                    set.connect(
                        R.id.txt_header_label,
                        ConstraintSet.START,
                        R.id.cl_header,
                        ConstraintSet.START,
                        (24 * scale + 0.5F).toInt()
                    )
                    set.applyTo(clHeader)
                }
                set.clone(clHeader)
                set.connect(
                    R.id.txt_header_label,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    (16 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.txt_header_label,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    (16 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.txt_header_label,
                    ConstraintSet.BOTTOM,
                    0
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    (16 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.cl_header,
                    ConstraintSet.START,
                    (24 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.cl_header,
                    ConstraintSet.END,
                    (24 * scale + 0.5F).toInt()
                )
                set.connect(
                    R.id.img_btn_back_header,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP
                )
                set.connect(
                    R.id.img_btn_info_1,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP
                )
                set.connect(
                    R.id.img_btn_info_2,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP
                )
                set.connect(
                    R.id.img_btn_info_3,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP
                )
                set.applyTo(clHeader)
            }
            // Search Only
            2 -> {
                clHeaderSearchEditText.isVisible = true
                txtHeaderLabel.isVisible = false
                if (!(this.enableBackButton) && !(this.showFirstIcon) && !(this.showSecondButton) && !(this.showThirdButton)) {
                    set.clear(R.id.cl_header_search_edit_text)
                    setConstraintSearchOnly(set, scale, R.id.cl_header, R.id.cl_header, 24, 24, ConstraintSet.START, ConstraintSet.END, false)
                }
                if (this.enableBackButton && !(this.showFirstIcon) && !(this.showSecondButton) && !(this.showThirdButton)) {
                    setConstraintSearchOnly(set, scale, R.id.img_btn_back_header, R.id.cl_header, 16, 24, ConstraintSet.END, ConstraintSet.END, true)
                }
                if (!(this.enableBackButton) && this.showFirstIcon && !(this.showSecondButton) && !(this.showThirdButton)) {
                    setConstraintSearchOnly(set, scale, R.id.cl_header, R.id.img_btn_info_1, 24, 18, ConstraintSet.START, ConstraintSet.START, true)
                }
                if (this.enableBackButton && this.showFirstIcon && !(this.showSecondButton) && !(this.showThirdButton)) {
                    setConstraintSearchOnly(set, scale, R.id.img_btn_back_header, R.id.img_btn_info_1, 16, 18, ConstraintSet.END, ConstraintSet.START, true)
                }
                if (!(this.enableBackButton) && this.showFirstIcon && this.showSecondButton && !(this.showThirdButton)) {
                    setConstraintSearchOnly(set, scale, R.id.cl_header, R.id.img_btn_info_2, 24, 18, ConstraintSet.START, ConstraintSet.START, true)
                }
                if (this.enableBackButton && this.showFirstIcon && this.showSecondButton && !(this.showThirdButton)) {
                    setConstraintSearchOnly(set, scale, R.id.img_btn_back_header, R.id.img_btn_info_2, 16, 18, ConstraintSet.END, ConstraintSet.START, true)
                }
                if (!(this.enableBackButton) && this.showFirstIcon && this.showSecondButton && this.showThirdButton) {
                    setConstraintSearchOnly(set, scale, R.id.cl_header, R.id.img_btn_info_3, 24, 18, ConstraintSet.START, ConstraintSet.START, true)
                }
                if (this.enableBackButton && this.showFirstIcon && this.showSecondButton && this.showThirdButton) {
                    setConstraintSearchOnly(set, scale, R.id.img_btn_back_header, R.id.img_btn_info_3, 16, 18, ConstraintSet.END, ConstraintSet.START, true)
                }
            }
        }
        txtSearchAutoComplete.setClearIcon(
            searchImage ?: R.drawable.ics_o_search,
            true
        )
        txtSearchAutoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtSearchAutoComplete.setClearIcon(
                    startDrawable = searchImage ?: R.drawable.ics_o_search,
                    isEnabled = true
                )
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        invalidate()
        requestLayout()
    }

    private fun setConstraintSearchOnly(
        set: ConstraintSet,
        scale: Float,
        startId: Int,
        endId: Int,
        startMargin: Int,
        endMargin: Int,
        startTarget: Int,
        endTarget: Int,
        matchParentWidth: Boolean
    ) {
        set.clone(clHeader)
        set.connect(
            R.id.cl_header_search_edit_text,
            ConstraintSet.TOP,
            R.id.cl_header,
            ConstraintSet.TOP,
            (8 * scale + 0.5F).toInt()
        )
        set.connect(
            R.id.cl_header_search_edit_text,
            ConstraintSet.BOTTOM,
            R.id.cl_header,
            ConstraintSet.BOTTOM,
            (8 * scale + 0.5F).toInt()
        )
        set.connect(
            R.id.cl_header_search_edit_text,
            ConstraintSet.START,
            startId,
            startTarget,
            (startMargin * scale + 0.5F).toInt()
        )
        set.connect(
            R.id.cl_header_search_edit_text,
            ConstraintSet.END,
            endId,
            endTarget,
            (endMargin * scale + 0.5F).toInt()
        )
        if (matchParentWidth) set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
        set.applyTo(clHeader)
    }

    fun setHeaderStyle(style: Int) {
        setHeaderStyle(headerStyle = style)
    }

    fun setSearchImage(image : Int) {
        setHeaderStyle(searchImage = image)
    }

    fun getTextFromSearch(): String {
        return txtSearchAutoComplete.text.toString()
    }

    fun searchTextFromKeyboard(searchListener: ((View) -> Unit)? = null) {
        txtSearchAutoComplete.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                view: TextView,
                actiodId: Int,
                keyEvent: KeyEvent?
            ): Boolean {
                if (actiodId == EditorInfo.IME_ACTION_SEARCH) {
                    searchListener?.invoke(view)
                    return true
                }
                return false
            }
        })
    }

    fun <T> searchArrayText(arrayList: ArrayList<T>) {
        val arrayAdapter = AutoCompleteArrayAdapter(context, arrayList)
        txtSearchAutoComplete.threshold = 0
        txtSearchAutoComplete.setAdapter(arrayAdapter)
    }

    fun setHeaderLabel(textLabel: String? = null) {
        txtHeaderLabel.text = textLabel ?: this.textLabel
    }

    fun setHeaderTextColor(textColor: Int? = null) {
        if (textColor != null) {
            txtHeaderLabel.setTextColor(textColor)
        } else {
            when (this.textLabelColor) {
                0 -> txtHeaderLabel.setTextColor(resources.getColor(R.color.shades5))
                1 -> txtHeaderLabel.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    fun setBackButton(
        backButtonImage: Int? = null
    ) {
        imgBtnBackHeader.isVisible = this.enableBackButton
        if (backButtonImage != null) {
            imgBtnBackHeader.setImageResource(backButtonImage)
        } else {
            imgBtnBackHeader.setImageDrawable(this.backButtonImage)
        }
        imgBtnBackHeader.setOnClickListener {
            val context = context as Activity
            context.finish()
        }
    }

    fun setBackButtonColor(color : Int? = null) {
        if (color != null){
            ImageViewCompat.setImageTintList(imgBtnBackHeader, ContextCompat.getColorStateList(context, color))
        } else {
            when (this.backButtonColor){
                0 -> ImageViewCompat.setImageTintList(imgBtnBackHeader, ContextCompat.getColorStateList(context, R.color.interpack6))
                1 -> ImageViewCompat.setImageTintList(imgBtnBackHeader, ContextCompat.getColorStateList(context, R.color.white))
            }
        }
    }

    fun setIconButton(
        firstIconImage: Int? = null,
        secondIconImage: Int? = null,
        thirdIconImage: Int? = null,
        firstIconListener: ((View) -> Unit)? = null,
        secondIconListener: ((View) -> Unit)? = null,
        thirdIconListener: ((View) -> Unit)? = null
    ) {
        if (this.showFirstIcon) {
            imgBtnIcon1.isVisible = this.showFirstIcon
            imgBtnIcon1.setOnClickListener {
                firstIconListener?.invoke(it)
            }
            if (firstIconImage != null) {
                imgBtnIcon1.setImageResource(firstIconImage)
            } else {
                imgBtnIcon1.setImageDrawable(this.firstIconImage)
            }
        } else {
            imgBtnIcon1.isVisible = this.showFirstIcon
        }
        if (this.showSecondButton && this.showFirstIcon) {
            imgBtnIcon2.isVisible = this.showSecondButton
            imgBtnIcon2.setOnClickListener {
                secondIconListener?.invoke(it)
            }
            if (secondIconImage != null) {
                imgBtnIcon2.setImageResource(secondIconImage)
            } else {
                imgBtnIcon2.setImageDrawable(this.secondIconImage)
            }
        } else {
            imgBtnIcon2.isVisible = this.showSecondButton
        }
        if (this.showThirdButton && this.showSecondButton && this.showFirstIcon) {
            imgBtnIcon3.isVisible = this.showSecondButton
            imgBtnIcon3.setOnClickListener {
                thirdIconListener?.invoke(it)
            }
            if (thirdIconImage != null) {
                imgBtnIcon3.setImageResource(thirdIconImage)
            } else {
                imgBtnIcon3.setImageDrawable(this.thirdIconImage)
            }
        } else {
            imgBtnIcon3.isVisible = this.showThirdButton
        }
    }

    fun addElevation(addElevation: Boolean? = null) {
        if (addElevation ?: this.addElevation) {
            clHeader.setPadding(1, 1, 1, 1)
            clHeader.background = resources.getDrawable(R.drawable.bg_header_shadow)
        }
    }

    fun setSearchHint(hint: String? = null) {
        txtSearchAutoComplete.hint = hint ?: this.searchHint
    }

    fun setTextButton(
        textBtnLabel: String? = null,
        txtBtnListener: ((View) -> Unit)? = null
    ) {
        txtButtonHeader.isVisible = this.enableTextButton
        txtButtonHeader.setOnClickListener {
            txtBtnListener?.invoke(it)
        }
        val scale = resources.displayMetrics.density
        val set = ConstraintSet()
        if (this.enableTextButton) {
            txtButtonHeader.text = textBtnLabel ?: this.textButtonText
            set.clone(clHeader)
            set.connect(
                R.id.cl_header_search_edit_text,
                ConstraintSet.END,
                R.id.txt_btn_header,
                ConstraintSet.START,
                (16 * scale + 0.5F).toInt()
            )
            set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
            set.applyTo(clHeader)
        }
    }

    fun setImageScan(
        imageScanner: Int? = null,
        imgScanListener: ((View) -> Unit)? = null
    ) {
        imgBtnScanHeader.isVisible = this.enableScanImage
        imgBtnScanHeader.setOnClickListener {
            imgScanListener?.invoke(it)
        }
        val set = ConstraintSet()
        val scale = resources.displayMetrics.density
        if (this.enableScanImage) {
            if (imageScanner != null) {
                imgBtnScanHeader.setImageResource(imageScanner)
            } else {
                imgBtnScanHeader.setImageDrawable(this.imgScanImage)
            }
            set.clone(clHeader)
            set.connect(
                R.id.cl_header_search_edit_text,
                ConstraintSet.START,
                R.id.img_btn_scan_header,
                ConstraintSet.END,
                (16 * scale + 0.5F).toInt()
            )
            set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
            set.applyTo(clHeader)
        }
    }

    fun setAssistiveText(enableAssistiveText: Boolean? = null, assistiveText: String? = null) {
        txtAssistiveHeader.isVisible = enableAssistiveText ?: this.enableAssistiveText
        if (enableAssistiveText ?: this.enableAssistiveText) {
            txtAssistiveHeader.text = assistiveText ?: this.assistiveText
        }
    }

    fun setErrorText(enableErrorText: Boolean? = null, errorText: String? = null) {
        txtErrorHeader.isVisible = enableErrorText ?: this.enableErrorText
        if (enableErrorText ?: this.enableErrorText) {
            txtErrorHeader.text = errorText ?: this.errorText
        }
    }
}
