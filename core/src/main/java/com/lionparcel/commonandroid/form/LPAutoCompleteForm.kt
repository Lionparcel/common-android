package com.lionparcel.commonandroid.form

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.lionparcel.commonandroid.R
import com.lionparcel.commonandroid.form.utils.AutoCompleteArrayAdapter

class LPAutoCompleteForm : ConstraintLayout {

    private var hint : String

    private val lpAutoCompleteTextView : LPAutoCompleteTextView
    private val lpTextInputLayoutAutoComplete : LPTextInputLayout

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
            } finally {
                recycle()
            }
        }

        lpAutoCompleteTextView = findViewById(R.id.lpAutoCompleteTextView)
        lpTextInputLayoutAutoComplete = findViewById(R.id.lpTextInputLayoutAutoComplete)
        lpAutoCompleteTextView.handleOnClearIconClick()
        lpTextInputLayoutAutoComplete.hint = hint
        setTextChangeState()

    }

    private fun setTextChangeState(){
        lpAutoCompleteTextView.let { editText->

            editText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    editText.setClearIcon()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })

        }
    }

    private fun TextView.setFont(setFont: Int){
        typeface = ResourcesCompat.getFont(context, setFont)
    }

    fun autoCompleteArrayTextHardCoded(arrayList : ArrayList<String>){
        val arrayAdapter = ArrayAdapter(context, R.layout.lp_layout_item_autocomplete, R.id.txtAutoComplete, arrayList)
        lpAutoCompleteTextView.threshold = 0
        lpAutoCompleteTextView.setAdapter(arrayAdapter)

    }

    fun <T> autoCompleteArrayText(arrayList : ArrayList<T>, data : String){
        val arrayAdapter = AutoCompleteArrayAdapter(context, arrayList) { it.let { data }}
        lpAutoCompleteTextView.threshold = 0
        lpAutoCompleteTextView.setAdapter(arrayAdapter)

    }

    fun <T> sortDataList(keyWords : String, list : List<T>) : List<T>{
        return sortDataList(keyWords, list)
    }

}