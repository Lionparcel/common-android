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
import android.widget.Button
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
class LPHeaderDA : ConstraintLayout {

    private var textLabel: String
    private var enableBackButton: Boolean
    private var backButtonImage: Drawable
    private var showFirstIcon: Boolean
    private var firstIconImage: Drawable
    private var showSecondButton: Boolean
    private var secondIconImage: Drawable
    private var showRightButton: Boolean
    private var rightButtonIcon: Drawable
    private var rightButtonText: String
    private var addElevation: Boolean
    private var enableSearch: Boolean
    private var searchHint: String
    private var enableTextLabel: Boolean
    private var enableAssistiveText: Boolean
    private var assistiveText: String
    private var enableErrorText: Boolean
    private var errorText: String

    private val clHeader: ConstraintLayout
    private val imgBtnBackHeader: ImageButton
    private val txtHeaderLabel: TextView
    private val imgBtnIcon1: ImageButton
    private val imgBtnIcon2: ImageButton
    private val rightButton: Button
    private val clHeaderSearchEditText: ConstraintLayout
    private val txtSearchAutoComplete: LPAutoCompleteTextView
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
        layoutInflater.inflate(R.layout.lp_layout_header_da, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPHeaderDA,
            0,
            0
        ).apply {
            try {
                textLabel = getString(R.styleable.LPHeaderDA_headerLabel).setString()
                enableBackButton = getBoolean(R.styleable.LPHeaderDA_showBackButton, false)
                backButtonImage =
                    getDrawable(
                        R.styleable.LPHeaderDA_backButtonImage
                    )?: resources.getDrawable(
                        R.drawable.ics_o_arrow_left_header)
                showFirstIcon = getBoolean(R.styleable.LPHeaderDA_showFirstIconButton, false)
                firstIconImage = getDrawable(
                    R.styleable.LPHeaderDA_firstIconImage
                ) ?: resources.getDrawable(R.drawable.ics_f_info_circle_interpack6)
                showSecondButton = getBoolean(R.styleable.LPHeaderDA_showSecondIconButton, false)
                secondIconImage =
                    getDrawable(
                        R.styleable.LPHeaderDA_secondIconImage
                    ) ?: resources.getDrawable(
                        R.drawable.ics_f_info_circle_interpack6
                    )
                showRightButton = getBoolean(R.styleable.LPHeaderDA_showRightButton, false)
                rightButtonIcon =
                    getDrawable(R.styleable.LPHeaderDA_rightButtonIcon)?: resources.getDrawable(R.drawable.ics_f_flash_on)
                rightButtonText = getString(R.styleable.LPHeaderDA_rightButtonText).setString()
                addElevation = getBoolean(R.styleable.LPHeaderDA_addElevation, false)
                enableSearch = getBoolean(R.styleable.LPHeaderDA_enableSearchView, false)
                searchHint = getString(R.styleable.LPHeaderDA_searchHint).setString()
                enableTextLabel = getBoolean(R.styleable.LPHeaderDA_enableTextLabel, true)
                enableAssistiveText = getBoolean(R.styleable.LPHeaderDA_enableAssistiveText, false)
                assistiveText = getString(R.styleable.LPHeaderDA_assistiveText).setString()
                enableErrorText = getBoolean(R.styleable.LPHeaderDA_enableErrorText, false)
                errorText = getString(R.styleable.LPHeaderDA_errorTextHeader).setString()

            } finally {
                recycle()
            }
        }

        clHeader = findViewById(R.id.cl_header_da)
        imgBtnBackHeader = findViewById(R.id.img_btn_back_header_da)
        txtHeaderLabel = findViewById(R.id.txt_header_label_da)
        imgBtnIcon1 = findViewById(R.id.img_btn_info_1_da)
        imgBtnIcon2 = findViewById(R.id.img_btn_info_2_da)
        rightButton = findViewById(R.id.btn_flash_da)
        clHeaderSearchEditText = findViewById(R.id.cl_header_search_edit_text_da)
        txtSearchAutoComplete = findViewById(R.id.txt_search_autocomplete_da)
        txtAssistiveHeader = findViewById(R.id.txt_assistive_header_da)
        txtErrorHeader = findViewById(R.id.txt_error_header_da)

        // set label text
        setHeaderLabel()
        // show back button
        setBackButton()
        // show icon
        setIconButton()
        // set right button
        setRightButton()
        // add shadow
        addElevation()

        txtSearchAutoComplete.handleOnClearIconClick()
        enableSearchView()
        setSearchHint()
        // enable or disable text label
        enableTextLabel()
        // set assistive text
        setAssistiveText()
        //set error text
        setErrorText()

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

    fun <T> searchArrayText(arrayList : ArrayList<T>) {
        val arrayAdapter = AutoCompleteArrayAdapter(context, arrayList)
        txtSearchAutoComplete.threshold = 0
        txtSearchAutoComplete.setAdapter(arrayAdapter)
    }

    fun setHeaderLabel(textLabel: String? = null) {
        txtHeaderLabel.text = textLabel ?: this.textLabel
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
                R.id.txt_header_label_da,
                ConstraintSet.START,
                R.id.img_btn_back_header_da,
                ConstraintSet.END,
                12
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
            if (firstIconImage != null) {
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
            if (secondIconImage != null) {
                imgBtnIcon2.setImageResource(secondIconImage)
            } else {
                imgBtnIcon2.setImageDrawable(this.secondIconImage)
            }
        }
    }

    fun setRightButton(
        showButton: Boolean? = null,
        buttonIcon: Int? = null,
        textButton: String? = null,
        buttonListener: ((View) -> Unit)? = null
    ) {
        if (showButton ?: this.showRightButton) {
            rightButton.isVisible = showButton ?: this.showRightButton
        }
        if (buttonIcon != null){
            rightButton.setCompoundDrawablesRelativeWithIntrinsicBounds(
                buttonIcon, 0, 0, 0
            )
        } else {
            rightButton.setCompoundDrawablesRelativeWithIntrinsicBounds(this.rightButtonIcon, null, null, null)
        }
        rightButton.text = textButton ?: this.rightButtonText
        rightButton.setOnClickListener {
            buttonListener?.invoke(it)
        }
    }

    fun addElevation(addElevation: Boolean? = null) {
        if (addElevation ?: this.addElevation) {
            clHeader.setPadding(1, 1, 1, 1)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                clHeader.elevation = 1F
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
                R.id.txt_header_label_da,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text_da,
                ConstraintSet.TOP
            )
            set.connect(
                R.id.img_btn_back_header_da,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text_da,
                ConstraintSet.TOP
            )
            set.connect(
                R.id.img_btn_info_1_da,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text_da,
                ConstraintSet.TOP
            )
            set.connect(
                R.id.img_btn_info_2_da,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text_da,
                ConstraintSet.TOP
            )
            set.connect(
                R.id.btn_flash_da,
                ConstraintSet.BOTTOM,
                R.id.cl_header_search_edit_text_da,
                ConstraintSet.TOP
            )
            set.applyTo(clHeader)
        }
        txtSearchAutoComplete.setClearIcon(
            searchImage ?: R.drawable.ics_o_search_shades3,
            enableSearch ?: this.enableSearch
        )
        txtSearchAutoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtSearchAutoComplete.setClearIcon(
                    startDrawable = searchImage ?: R.drawable.ics_o_search_shades3,
                    isEnabled = enableSearch ?: this@LPHeaderDA.enableSearch
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
        imageSearch : Int? = null
    ) {
        if (!(enableTextLabel ?: this.enableTextLabel)) {
            clHeaderSearchEditText.isVisible = true
            txtHeaderLabel.isVisible = false
            if (!(enableTextLabel ?: this.enableTextLabel) && !(enableBackButton
                    ?: this.enableBackButton) && !this.showFirstIcon && !this.showSecondButton && !this.showRightButton
            ) {
                val set = ConstraintSet()
                set.clear(R.id.cl_header_search_edit_text)
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.TOP,
                    R.id.cl_header_da,
                    ConstraintSet.TOP,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_da,
                    ConstraintSet.BOTTOM,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.START,
                    R.id.cl_header_da,
                    ConstraintSet.START
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.END,
                    R.id.cl_header_da,
                    ConstraintSet.END
                )
                set.applyTo(clHeader)
            }
            if (!(enableTextLabel
                    ?: this.enableTextLabel) && enableBackButton ?: this.enableBackButton && !this.showFirstIcon && !this.showSecondButton && !this.showRightButton
            ) {
                val set = ConstraintSet()
                set.clone(clHeader)
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.TOP,
                    R.id.cl_header_da,
                    ConstraintSet.TOP,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.BOTTOM,
                    R.id.cl_header_da,
                    ConstraintSet.BOTTOM,
                    16
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.START,
                    R.id.img_btn_back_header_da,
                    ConstraintSet.END,
                    12
                )
                set.connect(
                    R.id.cl_header_search_edit_text_da,
                    ConstraintSet.END,
                    R.id.cl_header_da,
                    ConstraintSet.END
                )
                set.constrainWidth(R.id.cl_header_search_edit_text_da, ConstraintSet.PARENT_ID)
                set.applyTo(clHeader)
            }
            invalidate()
            requestLayout()
            txtSearchAutoComplete.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    txtSearchAutoComplete.setClearIcon(
                        startDrawable = imageSearch?: R.drawable.ics_o_search_shades3,
                        isEnabled = !(enableTextLabel ?: this@LPHeaderDA.enableTextLabel)
                    )
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
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
