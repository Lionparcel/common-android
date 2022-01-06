package com.lionparcel.commonandroid.form

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroid.R

class LPPasswordLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var endIconMode: LinearLayout
    private var tflPassword: TextInputLayout
    private var edtPassword: TextInputEditText
    private var clearIcon: ImageView
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
            clearIcon = findViewById(R.id.ivClear)
            endIconMode = findViewById(R.id.endIconMode)
            edtPassword = findViewById(R.id.edtPassword)
            tflPassword = findViewById(R.id.tflPassword)

            hint = getString(R.styleable.LPPasswordLayout_android_hint).handle()
            setTextInputEditText()
        }
    }

    private fun setIconMode() {
        if (!edtPassword.text.isNullOrBlank()) {
            clearIcon.visibility = View.VISIBLE
            clearIcon.setOnClickListener {
                edtPassword.text?.clear()
            }
        } else {
            clearIcon.visibility = View.INVISIBLE
        }
    }

    private fun setTextInputEditText() {
        endIconMode.isVisible = true
        tflPassword.hint = hint
        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setIconMode()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    // password with helper text error
    fun showHelperTextError(defaultMessage: String) {
        tflPassword.let {
            it.helperText = defaultMessage
            val color = ContextCompat.getColorStateList(context, R.color.interpack6)
            it.setHelperTextColor(color)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.editText?.backgroundTintList = color
            }
        }
    }

    // password disabled
    fun disablePassword() {
        edtPassword.isEnabled = false
    }
}