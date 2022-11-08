package com.lionparcel.commonandroid.form

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.AutoCompleteArrayAdapter

class LPAutoCompleteForm : ConstraintLayout {

    private var hint : String
    private var editTextEnabled : Boolean
    private var editTextError : Boolean
    private var textError : String

    private val lpAutoCompleteTextView : LPAutoCompleteTextView
    private val lpTextInputLayoutAutoComplete : LPTextInputLayout
    private val txtAutoCompleteError : TextView

    private fun String?.setString() = this ?: ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_auto_complete_form, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPAutoCompleteForm,
            0,
            0
        ).apply {
            try {
                hint = getString(R.styleable.LPAutoCompleteForm_android_hint).setString()
                editTextEnabled = getBoolean(R.styleable.LPAutoCompleteForm_enabledEditText, true)
                editTextError = getBoolean(R.styleable.LPAutoCompleteForm_errorEnabled, false)
                textError = getString(R.styleable.LPAutoCompleteForm_errorText).setString()
            } finally {
                recycle()
            }
        }

        lpAutoCompleteTextView = findViewById(R.id.lpAutoCompleteTextView)
        lpTextInputLayoutAutoComplete = findViewById(R.id.lpTextInputLayoutAutoComplete)
        txtAutoCompleteError = findViewById(R.id.txtAutoCompleteError)

        lpAutoCompleteTextView.handleOnClearIconClick()
        //set hint
        lpTextInputLayoutAutoComplete.hint = hint
        //setText errorText
        txtAutoCompleteError.text = textError
        //enable or disable error and edtText
        setTextChangeState(editTextEnabled, editTextError)
        lpAutoCompleteTextView.isEnabled = editTextEnabled
        changeTextColorDisabled(editTextEnabled)


    }

    private fun setTextChangeState(isEnable : Boolean, isError: Boolean) {
        lpAutoCompleteTextView.let {

            if (isError) {
                it.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus || !lpAutoCompleteTextView.text.isNullOrEmpty()) {
                        changeStateViewTextViewError(!isError)
                    } else if (!hasFocus && lpAutoCompleteTextView.text.isNullOrEmpty()) {
                        changeStateViewTextViewError(isError)
                    }
                }
            }
        }
        lpAutoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setEnabledEditText(isEnable)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    private fun LPAutoCompleteTextView.changeTextColor(color: Int) {
        setTextColor(ContextCompat.getColor(context, color))
    }

    private fun changeTextColorDisabled(isTextAreaEnabled : Boolean) {
        val textColor = if (isTextAreaEnabled) R.color.shades5 else R.color.shades3
        lpAutoCompleteTextView.changeTextColor(textColor)
    }

    fun changeStateViewTextViewError(isError : Boolean) {
        val textColor = if (isError) {
            R.color.shades3
        } else R.color.shades5
        val color = if (isError) {
            ContextCompat.getColorStateList(context, R.color.interpack6)
        } else ContextCompat.getColorStateList(context, R.color.shades2)
        editTextError = isError
        lpTextInputLayoutAutoComplete.isSelected = isError
        txtAutoCompleteError.isVisible = isError
        lpAutoCompleteTextView.changeTextColor(textColor)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lpAutoCompleteTextView.backgroundTintList = color
        }
    }


    fun <T> autoCompleteArrayText(arrayList : ArrayList<T>) {
        val arrayAdapter = AutoCompleteArrayAdapter(context, arrayList)
        lpAutoCompleteTextView.threshold = 0
        lpAutoCompleteTextView.setAdapter(arrayAdapter)

    }

    fun getAutoCompleteText() : String {
        return lpAutoCompleteTextView.text.toString()
    }

    fun setEnabledEditText(enabledView : Boolean) {
        editTextEnabled = enabledView
        lpAutoCompleteTextView.isEnabled = editTextEnabled
        changeTextColorDisabled(editTextEnabled)
        lpAutoCompleteTextView.setClearIcon(isEnabled = editTextEnabled)
        invalidate()
        requestLayout()
    }
    fun setHint(hintText : String) {
        lpTextInputLayoutAutoComplete.hint = hintText
    }

    fun setErrorText(errorText : String) {
        txtAutoCompleteError.text = errorText
    }

}