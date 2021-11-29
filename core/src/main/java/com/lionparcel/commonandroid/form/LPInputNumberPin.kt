package com.lionparcel.commonandroid.form

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.widget.doOnTextChanged
import com.google.android.flexbox.FlexboxLayout
import com.lionparcel.commonandroid.R

class LPInputNumberPin : FrameLayout {

    private val blinkingAnim by lazy {
        context?.let { AnimationUtils.loadAnimation(it, R.anim.blinking_view) }
    }

    private var edtPinNumber: EditText
    private var flxPinNumber: FlexboxLayout

    var onShowKeyboard: ((EditText) -> Unit)? = null
    private var callbackOnPinNumberCompleted: ((String) -> Unit)? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0 )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_pin_input_number, this, true)
        edtPinNumber = findViewById(R.id.edtPinNumber)
        flxPinNumber = findViewById(R.id.flxPinNumber)
        flxPinNumber.removeAllViews()
        (0 until 6)
            .map {
                layoutInflater.inflate(
                    R.layout.lp_pin_single_number_view,
                    flxPinNumber,
                    false
                )
            }
            .map(flxPinNumber::addView)
        edtPinNumber.doOnTextChanged { _, _, _, _ ->
            onPinNumberChanged()
        }
        flxPinNumber.setOnClickListener { onShowKeyboard?.invoke(edtPinNumber) }
    }

    private fun onPinNumberChanged() {
        val cursorPosition = edtPinNumber.text.toString().length
        flxPinNumber.forEachIndexed { index, view ->

            val txtPinNumber = view.findViewById<TextView>(R.id.txtPinNumber)
            val ivPinNumber = view.findViewById<ImageView>(R.id.ivPinNumber)
            val pinNumberString = edtPinNumber.text.toString()

            when {
                index < pinNumberString.length -> {
                    txtPinNumber.text = pinNumberString[index].toString()
                    val filledPinNumberColor = ContextCompat.getColor(context, R.color.shades5)
                    filledPinNumberColor.let(txtPinNumber::setTextColor)
                    txtPinNumber.setTypeface(txtPinNumber.typeface, Typeface.BOLD)
                    ivPinNumber.setImageResource(R.drawable.bg_circle_shades5_20)
                    txtPinNumber.clearAnimation()
                }
                index == cursorPosition && cursorPosition != 6 -> {
                    ivPinNumber.setImageResource(R.drawable.bg_circle_shades2_20)
                    txtPinNumber.startAnimation(blinkingAnim)
                }
                else -> {
                    txtPinNumber.text = String()
                    txtPinNumber.clearAnimation()
                }
            }
        }

        if (edtPinNumber.length() == 6) {
            callbackOnPinNumberCompleted?.invoke(edtPinNumber.toString())
            onHandlerPinError()
        }
    }

    private fun onHandlerPinError() {
        flxPinNumber.forEach { view ->
            val ivPinNumber = view.findViewById<ImageView>(R.id.ivPinNumber)
            ivPinNumber.setImageResource(R.drawable.bg_circle_interpack6_20)
        }
    }
}