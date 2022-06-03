package com.lionparcel.commonandroid.form

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.lionparcel.commonandroid.R

class LPAutoCompleteForm : ConstraintLayout {

    private val lpAutoCompleteTextView : LPAutoCompleteTextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_auto_complete_form, this, true)

        lpAutoCompleteTextView = findViewById(R.id.lpAutoCompleteTextView)
        lpAutoCompleteTextView.handleOnClearIconClick()
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
}