package com.lionparcel.commonandroid.form

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.core.widget.doOnTextChanged
import com.google.android.flexbox.FlexboxLayout
import com.lionparcel.commonandroid.R
import kotlinx.android.synthetic.main.lp_otp_single_number_view.view.*

class LPInputNumberOTP : FrameLayout {

    private val blinkingAnim by lazy {
        context?.let { AnimationUtils.loadAnimation(it, R.anim.blinking_view) }
    }

    private var edtOtpNumber: EditText
    private var flxOtpNumber: FlexboxLayout

    var onShowKeyboard: ((EditText) -> Unit)? = null
    var callbackOnOtpNumberCompleted: ((String) -> Unit)? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0 )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.lp_otp_input_number, this, true)
        edtOtpNumber = findViewById(R.id.edtOtpNumber)
        flxOtpNumber = findViewById(R.id.flxOtpNumber)
        flxOtpNumber.removeAllViews()
        (0 until 6)
            .map {
                layoutInflater.inflate(
                    R.layout.lp_otp_single_number_view,
                    flxOtpNumber,
                    false
                )
            }
            .map(flxOtpNumber::addView)
        edtOtpNumber.doOnTextChanged { _, _, _, _ ->
            onOtpNumberChanged()
        }
        flxOtpNumber.setOnClickListener { onShowKeyboard?.invoke(edtOtpNumber) }

    }

    private fun onOtpNumberChanged() {
        val cursorPosition = edtOtpNumber.text.toString().length
        flxOtpNumber.forEachIndexed { index, view ->
            val otpNumberString = edtOtpNumber.text.toString()
            when {
                index < otpNumberString.length -> {
                    view.txtNumber.text = otpNumberString[index].toString()
                    val filledOtpNumberColor = ContextCompat.getColor(context, R.color.shades5)
                    filledOtpNumberColor.let(txtNumber::setTextColor)
                    view.txtNumber.setTypeface(txtNumber.typeface, Typeface.BOLD)
                    view.ivNumberOutline.setImageResource(R.drawable.bg_shades5_outline_rounded_3)
                    view.txtNumber.clearAnimation()
                }
                index == cursorPosition && cursorPosition != 6 -> {
                    val cursorColor = ContextCompat.getColor(context, R.color.interpack6)
                    cursorColor.let(txtNumber::setTextColor)
                    view.ivNumberOutline.setImageResource(R.drawable.bg_shades2_outline_rounded_3)
                    view.txtNumber.text = "|"
                    view.txtNumber.startAnimation(blinkingAnim)
                }
                else -> {
                    view.txtNumber.text = String()
                    view.txtNumber.clearAnimation()
                }
            }
        }

        if (edtOtpNumber.length() == 6) {
            callbackOnOtpNumberCompleted?.invoke(edtOtpNumber.toString())
        }
    }

}