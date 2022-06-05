package com.lionparcel.commonandroid.form

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.lionparcel.commonandroid.R

class LPCountInput : LinearLayout {

    private var imgBtnCountInputMin : ImageButton
    private var txtCountInputValue : TextView
    private var imgBtnCountInputPlus : ImageButton

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
         val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_count_input_view, this, true)
        imgBtnCountInputMin = findViewById(R.id.imgBtnCountInputMin)
        txtCountInputValue = findViewById(R.id.txtCountInputValue)
        imgBtnCountInputPlus = findViewById(R.id.imgBtnCountInputPlus)

        txtCountInputValue.text = "1"
        txtCountInputValue.addTextChangedListener(object :TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkButtonDisable(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        imgBtnCountInputMin.apply {
            if (imgBtnCountInputMin.isClickable){
                imgBtnCountInputMin.setOnClickListener {
                    val textString = txtCountInputValue.text.toString()
                    val textInt = textString.toInt()
                    val textValue = textInt - 1
                    txtCountInputValue.text = "${textValue.toString()}"
                }
            }
        }
        imgBtnCountInputPlus.apply {
            if (imgBtnCountInputPlus.isClickable){
                imgBtnCountInputPlus.setOnClickListener {
                    val textString = txtCountInputValue.text.toString()
                    val textInt = textString.toInt()
                    val textValue = textInt + 1
                    txtCountInputValue.text = "${textValue.toString()}"
                }
            }
        }
    }

    private fun checkButtonDisable(text : String) {
        if (text == "1"){
            minBtnDisable(false)
        } else if (text != "1"){
            minBtnDisable(true)
        }
        if (text == "99"){
            plusBtnDisable(false)
        } else if (text != "99"){
            plusBtnDisable(true)
        }
    }

    private fun minBtnDisable(isClickable : Boolean) {
        imgBtnCountInputMin.isClickable = isClickable
        val btnAlpha = if (isClickable) 1f else 0.5f
        imgBtnCountInputMin.alpha = btnAlpha

    }

    private fun plusBtnDisable(isClickable : Boolean){
        imgBtnCountInputPlus.isClickable = isClickable
        val btnAlpha = if (isClickable) 1f else 0.5f
        imgBtnCountInputPlus.alpha = btnAlpha
    }
}