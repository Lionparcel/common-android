package com.lionparcel.commonandroid.form

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R

class LPTextArea : ConstraintLayout{

    private var counterMaxLength : Int
    private var hint : String
    private var errorEnabled : Boolean
    private var errorTextEnabled : Boolean
    private var counterTextEnabled : Boolean
    private var supportedTextEnabled : Boolean
    private var clTextAreaParent : ConstraintLayout
    private var txtAreaLabel : TextView
    private var llEditText : LinearLayout
    private var lpEditTextArea : LPTextInputEditText
    private var txtAreaCounter : TextView
    private var txtAreaAlert : TextView
    private var txtAreaError : TextView

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_text_area_view, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPTextArea,
            0,
            0,
        ).apply {
            clTextAreaParent = findViewById(R.id.clTextAreaParent)
            txtAreaLabel = findViewById(R.id.txtAreaLabel)
            llEditText = findViewById(R.id.llEditText)
            lpEditTextArea = findViewById(R.id.lpEditTextArea)
            txtAreaCounter = findViewById(R.id.txtAreaCounter)
            txtAreaAlert = findViewById(R.id.txtAreaAlert)
            txtAreaError = findViewById(R.id.txtAreaError)

            hint = getString(R.styleable.LPTextArea_android_hint).setString()
            counterMaxLength = getInt(R.styleable.LPTextArea_android_maxLength, 0)
            errorEnabled = getBoolean(R.styleable.LPTextArea_errorEnabled, true)
            errorTextEnabled = getBoolean(R.styleable.LPTextArea_errorTextEnabled, true)
            counterTextEnabled = getBoolean(R.styleable.LPTextArea_counterTextEnabled, true)
            supportedTextEnabled = getBoolean(R.styleable.LPTextArea_supportedTextEnabled, true)
            txtAreaLabel.text = getString(R.styleable.LPTextArea_textLabel).setString()
            if (supportedTextEnabled) txtAreaAlert.text = getString(R.styleable.LPTextArea_textSupported).setString() else txtAreaAlert.isVisible = false
            if (errorTextEnabled) txtAreaError.text = getString(R.styleable.LPTextArea_textError).setString() else txtAreaError.isVisible = false
            lpEditTextArea.isEnabled = getBoolean(R.styleable.LPTextArea_enabledView, true)
            setTextInputEditText(counterMaxLength, hint, errorEnabled, errorTextEnabled, counterTextEnabled, supportedTextEnabled)
        }

    }

    private fun setTextInputEditText(
        maxLength : Int,
        setHint : String,
        isError: Boolean,
        isErrorTextEnabled: Boolean,
        isCounterTextEnabled : Boolean,
        isSupportedTextEnabled : Boolean) {
        lpEditTextArea.hint = setHint
        txtAreaCounter.text = "0/$maxLength"
        if (isError){
            lpEditTextArea.onFocusChangeListener = View.OnFocusChangeListener{ view, b ->
                if (b || !lpEditTextArea.text.isNullOrEmpty()){
                    changeStateViewTextArea(!isError, isSupportedTextEnabled, isErrorTextEnabled)
                } else if(!b && lpEditTextArea.text.isNullOrEmpty()) {
                    changeStateViewTextArea(isError, isSupportedTextEnabled, isErrorTextEnabled)
                }
            }
        }
        if(isCounterTextEnabled){
            lpEditTextArea.setMaxLength(maxLength)
            lpEditTextArea.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    txtAreaCounter.text = "${p0?.toString()?.length}/$maxLength"
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        } else txtAreaCounter.isVisible = false
    }

    private fun LPTextInputEditText.setMaxLength(maxLength: Int){
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

    private fun changeStateViewTextArea(isError : Boolean, isSupportedTextEnabled: Boolean, isErrorTextEnabled: Boolean){
        val textColor = if (isError) R.color.interpack6 else R.color.shades3
        llEditText.isSelected = isError
        txtAreaCounter.changeTextColor(textColor)
        if (isSupportedTextEnabled) txtAreaAlert.isVisible = !isError else txtAreaAlert.isVisible = false
        if (isErrorTextEnabled) txtAreaError.isVisible = isError else txtAreaError.isVisible = false
    }

    private fun TextView.changeTextColor(color: Int) {
        setTextColor(ContextCompat.getColor(context, color))
    }

    // function for get text from LPTextArea
    fun getTextAreaText() : String {
        return lpEditTextArea.text.toString()
    }

}