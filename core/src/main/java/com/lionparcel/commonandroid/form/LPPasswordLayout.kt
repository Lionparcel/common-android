package com.lionparcel.commonandroid.form

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroid.R

class LPPasswordLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var endIconMode: LinearLayout
    private lateinit var tflPassword: TextInputLayout
    private lateinit var edtPassword: TextInputEditText
    private lateinit var clearIcon: ImageView
    private var hint: String

    private fun String?.handle() = this ?: ""

    init {
        inflate(context, R.layout.lp_password_view, this)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LPPasswordLayout,
            0,
            0
        ).apply {
            hint = getString(R.styleable.LPPasswordLayout_android_hint).handle()
            setIconMode()
        }
    }

    private fun setIconMode() {
        clearIcon = findViewById(R.id.ivClear)
        endIconMode = findViewById(R.id.endIconMode)
        edtPassword = findViewById(R.id.edtPassword)
        tflPassword = findViewById(R.id.tflPassword)
        endIconMode.isVisible = true
        tflPassword.hint = hint
        edtPassword.doOnTextChanged { _, _, _, _ ->
            if (!edtPassword.text.isNullOrBlank()) {
                clearIcon.visibility = View.VISIBLE
                clearIcon.setOnClickListener {
                    edtPassword.text?.clear()
                }
            } else {
                clearIcon.visibility = View.INVISIBLE
            }
        }
    }

    fun helperEnable(errorMessage: String) {
        val helperText = findViewById<TextInputLayout>(R.id.tflPassword)
        helperText.error = errorMessage
    }
}