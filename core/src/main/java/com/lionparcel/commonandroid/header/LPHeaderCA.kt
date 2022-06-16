package com.lionparcel.commonandroid.header

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.LPAutoCompleteTextView

class LPHeaderCA : LinearLayout {

    private val llHeaderParent : LinearLayout
    private val llHeader : LinearLayout
    private val llHeaderText : LinearLayout
    private val imgBtnBackHeader : ImageButton
    private val txtHeaderLabel : TextView
    private val imgBtnIcon1 : ImageButton
    private val imgBtnIcon2 : ImageButton
    private val clHeaderSearch : ConstraintLayout
    private val clHeaderSearchEditText : ConstraintLayout
    private val imgBtnSearch : ImageButton
    private val txtSearchAutoComplete : LPAutoCompleteTextView

    private var textLabel : String
    private var enableBackButton : Boolean
    private var showFirstIcon : Boolean
    private var showSecondButton : Boolean
    private var addElevation : Boolean
    private var enableSearch : Boolean
    private var searchHint : String

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
                addElevation = getBoolean(R.styleable.LPHeaderCA_addElevation, false)
                enableSearch = getBoolean(R.styleable.LPHeaderCA_enableSearchView, false)
                searchHint = getString(R.styleable.LPHeaderCA_searchHint).setString()

            } finally {
                recycle()
            }
        }

        llHeaderParent = findViewById(R.id.ll_header_parent)
        llHeader = findViewById(R.id.ll_header)
        llHeaderText = findViewById(R.id.ll_header_text)
        imgBtnBackHeader = findViewById(R.id.img_btn_back_header)
        txtHeaderLabel = findViewById(R.id.txt_header_label)
        imgBtnIcon1 = findViewById(R.id.img_btn_info_1)
        imgBtnIcon2 = findViewById(R.id.img_btn_info_2)
        clHeaderSearch = findViewById(R.id.cl_header_search)
        clHeaderSearchEditText = findViewById(R.id.cl_header_search_edit_text)
        imgBtnSearch = findViewById(R.id.img_btn_search)
        txtSearchAutoComplete = findViewById(R.id.txt_search_autocomplete)

        // set label text
        setHeaderLabel(textLabel)
        // show back button
        showBackButton(enableBackButton)
        // show icon
        showIconButton(showFirstIcon, showSecondButton)
        // add shadow
        addElevation(addElevation)

        txtSearchAutoComplete.handleOnClearIconClick()
        enableSearchView(enableSearch)
        setSearchHint(searchHint)
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

    @SuppressLint("UseCompatLoadingForDrawables")
    fun addElevation(addElevation : Boolean){
        if(addElevation){
            llHeaderParent.setPadding(1,1,1,1)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                llHeader.elevation = 4F
            }
            llHeader.background = resources.getDrawable(R.drawable.bg_header_background)
        }
    }

    fun enableSearchView(isEnable: Boolean){
        clHeaderSearch.isVisible = isEnable
        invalidate()
        requestLayout()
        txtSearchAutoComplete.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txtSearchAutoComplete.setClearIcon(isEnabled = isEnable)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun setSearchHint(hint : String){
        txtSearchAutoComplete.hint = hint
    }
}