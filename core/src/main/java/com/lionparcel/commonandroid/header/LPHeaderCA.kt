package com.lionparcel.commonandroid.header

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
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
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.LPAutoCompleteTextView
import com.lionparcel.commonandroid.form.utils.AutoCompleteArrayAdapter

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
class LPHeaderCA : ConstraintLayout {

    private var textLabel: String
    private var textLabelColor : Int
    private var enableBackButton: Boolean
    private var backButtonImage: Drawable
    private var showFirstIcon: Boolean
    private var firstIconImage: Drawable
    private var showSecondButton: Boolean
    private var secondIconImage: Drawable
    private var addElevation: Boolean
    private var enableSearch: Boolean
    private var searchHint: String
    private var enableTextLabel: Boolean
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
                backButtonImage =
                    getDrawable(R.styleable.LPHeaderCA_backButtonImage)?: resources.getDrawable(R.drawable.ic_o_arrow_left_alt)
                showFirstIcon = getBoolean(R.styleable.LPHeaderCA_showFirstIconButton, false)
                firstIconImage =
                    getDrawable(R.styleable.LPHeaderCA_firstIconImage)?: resources.getDrawable(R.drawable.ics_o_info_circle)
                showSecondButton = getBoolean(R.styleable.LPHeaderCA_showSecondIconButton, false)
                secondIconImage =
                    getDrawable(R.styleable.LPHeaderCA_secondIconImage)?: resources.getDrawable(R.drawable.ics_o_info_circle)
                addElevation = getBoolean(R.styleable.LPHeaderCA_addElevation, false)
                enableSearch = getBoolean(R.styleable.LPHeaderCA_enableSearchView, false)
                searchHint = getString(R.styleable.LPHeaderCA_searchHint).setString()
                enableTextLabel = getBoolean(R.styleable.LPHeaderCA_enableTextLabel, true)
                enableTextButton = getBoolean(R.styleable.LPHeaderCA_enableTextButton, false)
                textButtonText = getString(R.styleable.LPHeaderCA_txtButtonText).setString()
                enableScanImage = getBoolean(R.styleable.LPHeaderCA_enableScanImage, false)
                imgScanImage = getDrawable(R.styleable.LPHeaderCA_imageScan)?: resources.getDrawable(R.drawable.ics_o_scan)
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
        clHeaderSearchEditText = findViewById(R.id.cl_header_search_edit_text)
        txtSearchAutoComplete = findViewById(R.id.txt_search_autocomplete)
        txtButtonHeader = findViewById(R.id.txt_btn_header)
        imgBtnScanHeader = findViewById(R.id.img_btn_scan_header)
        txtAssistiveHeader = findViewById(R.id.txt_assistive_header)
        txtErrorHeader = findViewById(R.id.txt_error_header)

        // set label text
        setHeaderLabel()
        // set label text color
        setHeaderTextColor()
        // show back button
        setBackButton()
        // show icon
        setIconButton()
        // add shadow
        addElevation()

        txtSearchAutoComplete.handleOnClearIconClick()
        enableSearchView()
        setSearchHint()
        // enable or disable text label
        enableTextLabel()
        // set text button label
        setTextButton()
        // set image scan
        setImageScan()
        // set assistive text
        setAssistiveText()
        //set error text
        setErrorText()

    }
    fun getTextFromSearch() : String {
        return txtSearchAutoComplete.text.toString()
    }

    fun searchTextFromKeyboard(searchListener : ((View) -> Unit)? = null){
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

    fun <T> searchArrayText(arrayList : ArrayList<T>) {
        val arrayAdapter = AutoCompleteArrayAdapter(context, arrayList)
        txtSearchAutoComplete.threshold = 0
        txtSearchAutoComplete.setAdapter(arrayAdapter)
    }

    fun setHeaderLabel(textLabel: String? = null) {
        txtHeaderLabel.text = textLabel ?: this.textLabel
    }

    fun setHeaderTextColor(textColor : Int? = null){
        if (textColor != null){
            txtHeaderLabel.setTextColor(textColor)
        } else {
            when (this.textLabelColor){
                0 -> txtHeaderLabel.setTextColor(resources.getColor(R.color.shades5))
                1 -> txtHeaderLabel.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    fun setBackButton(
        enableBackButton: Boolean? = null,
        enableTextLabel: Boolean? = null,
        backButtonImage: Int? = null
    ) {
        imgBtnBackHeader.isVisible = enableBackButton ?: this.enableBackButton
        if (backButtonImage != null){
            imgBtnBackHeader.setImageResource(backButtonImage)
        } else {
            imgBtnBackHeader.setImageDrawable(this.backButtonImage)
        }
        if (enableBackButton ?: this.enableBackButton && enableTextLabel ?: this.enableTextLabel) {
            val set = ConstraintSet()
            set.clone(clHeader)
            set.connect(
                R.id.txt_header_label,
                ConstraintSet.START,
                R.id.img_btn_back_header,
                ConstraintSet.END,
                16
            )
            set.applyTo(clHeader)
        }
        imgBtnBackHeader.setOnClickListener {
            val context = context as Activity
            context.finish()
        }
    }

    fun setIconButton(
        showFirstIcon: Boolean? = null,
        showSecondIcon: Boolean? = null,
        firstIconImage: Int? = null,
        secondIconImage: Int? = null,
        firstIconListener: ((View) -> Unit)? = null,
        secondIconListener: ((View) -> Unit)? = null
    ) {
        if (showFirstIcon ?: this.showFirstIcon) {
            imgBtnIcon1.isVisible = showFirstIcon ?: this.showFirstIcon
            imgBtnIcon1.setOnClickListener {
                firstIconListener?.invoke(it)
            }
            if (firstIconImage != null){
                imgBtnIcon1.setImageResource(firstIconImage)
            } else {
                imgBtnIcon1.setImageDrawable(this.firstIconImage)
            }
        }
        if (showSecondIcon ?: this.showSecondButton && showFirstIcon ?: this.showFirstIcon) {
            imgBtnIcon2.isVisible = showSecondIcon ?: this.showSecondButton
            imgBtnIcon2.setOnClickListener {
                secondIconListener?.invoke(it)
            }
            if (secondIconImage != null){
                imgBtnIcon1.setImageResource(secondIconImage)
            } else {
                imgBtnIcon1.setImageDrawable(this.secondIconImage)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun addElevation(addElevation: Boolean? = null) {
        if (addElevation ?: this.addElevation) {
            clHeader.setPadding(1, 1, 1, 1)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                clHeader.elevation = 4F
            }
            clHeader.background = resources.getDrawable(R.drawable.bg_header_background)
        }
    }

    fun enableSearchView(enableSearch: Boolean? = null, searchImage: Int? = null) {
        clHeaderSearchEditText.isVisible = enableSearch ?: this.enableSearch
        if (enableSearch ?: this.enableSearch) {
            val set = ConstraintSet()
            set.clone(clHeader)
            set.connect(
                R.id.txt_header_label,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text,
                ConstraintSet.TOP
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
            set.applyTo(clHeader)
        }
        txtSearchAutoComplete.setClearIcon(
            searchImage ?: R.drawable.ics_o_search,
            enableSearch ?: this@LPHeaderCA.enableSearch
        )
        txtSearchAutoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtSearchAutoComplete.setClearIcon(
                    startDrawable = searchImage ?: R.drawable.ics_o_search,
                    isEnabled = enableSearch ?: this@LPHeaderCA.enableSearch
                )
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    fun setSearchHint(hint: String? = null) {
        txtSearchAutoComplete.hint = hint ?: this.searchHint
    }

    fun enableTextLabel(
        enableTextLabel: Boolean? = null,
        enableBackButton: Boolean? = null,
        enableIcon1: Boolean? = null,
        enableIcon2: Boolean? = null,
        searchImage: Int? = null
    ) {
        if (!(enableTextLabel ?: this.enableTextLabel)) {
            clHeaderSearchEditText.isVisible = true
            txtHeaderLabel.isVisible = false
            if (!(enableTextLabel ?: this.enableTextLabel) && !(enableBackButton
                    ?: this.enableBackButton) && !(enableIcon1
                    ?: this.showFirstIcon) && !(enableIcon2 ?: this.showSecondButton)
            ) {
                val set = ConstraintSet()
                set.clear(R.id.cl_header_search_edit_text)
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.cl_header,
                    ConstraintSet.START
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.cl_header,
                    ConstraintSet.END
                )
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel
                    ?: this.enableTextLabel) && enableBackButton ?: this.enableBackButton && !(enableIcon1
                    ?: this.showFirstIcon) && !(enableIcon2 ?: this.showSecondButton)
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.img_btn_back_header,
                    ConstraintSet.END,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.cl_header,
                    ConstraintSet.END
                )
                set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel ?: this.enableTextLabel) && !(enableBackButton
                    ?: this.enableBackButton) && enableIcon1 ?: this.showFirstIcon && !(enableIcon2
                    ?: this.showSecondButton)
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.cl_header,
                    ConstraintSet.START
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.img_btn_info_1,
                    ConstraintSet.START,
                    18
                )
                set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel
                    ?: this.enableTextLabel) && enableBackButton ?: this.enableBackButton && enableIcon1 ?: this.showFirstIcon && !(enableIcon2
                    ?: this.showSecondButton)
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.img_btn_back_header,
                    ConstraintSet.END,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.img_btn_info_1,
                    ConstraintSet.START,
                    18
                )
                set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel ?: this.enableTextLabel) && !(enableBackButton
                    ?: this.enableBackButton) && enableIcon1 ?: this.showFirstIcon && enableIcon2 ?: this.showSecondButton
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.cl_header,
                    ConstraintSet.START
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.img_btn_info_2,
                    ConstraintSet.START,
                    18
                )
                set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel
                    ?: this.enableTextLabel) && enableBackButton ?: this.enableBackButton && enableIcon1 ?: this.showFirstIcon && enableIcon2 ?: this.showSecondButton
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.TOP,
                    R.id.cl_header,
                    ConstraintSet.TOP,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header,
                    ConstraintSet.BOTTOM,
                    8
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.START,
                    R.id.img_btn_back_header,
                    ConstraintSet.END,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text,
                    ConstraintSet.END,
                    R.id.img_btn_info_2,
                    ConstraintSet.START,
                    18
                )
                set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            invalidate()
            requestLayout()
            txtSearchAutoComplete.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    txtSearchAutoComplete.setClearIcon(
                        startDrawable = searchImage?: R.drawable.ics_o_search,
                        isEnabled = !(enableTextLabel ?: this@LPHeaderCA.enableTextLabel)
                    )
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

    }

    fun setTextButton(
        enableTextBtn: Boolean? = null,
        textBtnLabel: String? = null,
        txtBtnListener: ((View) -> Unit)? = null
    ) {
        txtButtonHeader.isVisible = enableTextBtn ?: this.enableTextButton
        txtButtonHeader.setOnClickListener {
            txtBtnListener?.invoke(it)
        }
        if (enableTextBtn ?: this.enableTextButton) {
            txtButtonHeader.text = textBtnLabel ?: this.textButtonText
            val set = ConstraintSet()
            set.clone(clHeader)
            set.connect(
                R.id.cl_header_search_edit_text,
                ConstraintSet.END,
                R.id.txt_btn_header,
                ConstraintSet.START,
                16
            )
            set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
            set.applyTo(clHeader)
        }
    }

    fun setImageScan(
        enableScanImage: Boolean? = null,
        imageScanner: Int? = null,
        imgScanListener: ((View) -> Unit)? = null
    ) {
        imgBtnScanHeader.isVisible = enableScanImage ?: this.enableScanImage
        imgBtnScanHeader.setOnClickListener {
            imgScanListener?.invoke(it)
        }
        if (enableScanImage ?: this.enableScanImage) {
            if (imageScanner != null){
                imgBtnScanHeader.setImageResource(imageScanner)
            } else {
                imgBtnScanHeader.setImageDrawable(this.imgScanImage)
            }
            val set = ConstraintSet()
            set.clone(clHeader)
            set.connect(
                R.id.cl_header_search_edit_text,
                ConstraintSet.START,
                R.id.img_btn_scan_header,
                ConstraintSet.END,
                16
            )
            set.constrainWidth(R.id.cl_header_search_edit_text, ConstraintSet.PARENT_ID)
            set.applyTo(clHeader)
        }
    }

    fun setAssistiveText(enableAssistiveText: Boolean? = null, assistiveText: String? = null) {
        if (enableAssistiveText ?: this.enableAssistiveText) {
            txtAssistiveHeader.isVisible = enableAssistiveText ?: this.enableAssistiveText
            txtAssistiveHeader.text = assistiveText ?: this.assistiveText
        }
    }

    fun setErrorText(enableErrorText: Boolean? = null, errorText: String? = null) {
        if (enableErrorText ?: this.enableErrorText) {
            txtErrorHeader.isVisible = enableErrorText ?: this.enableErrorText
            txtErrorHeader.text = errorText ?: this.errorText
        }
    }
}
