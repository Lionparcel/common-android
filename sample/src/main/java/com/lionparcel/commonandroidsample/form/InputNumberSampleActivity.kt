package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPInputNumberPin
import com.lionparcel.commonandroid.form.LPInputNumberOtp
import com.lionparcel.commonandroidsample.R

class InputNumberSampleActivity : AppCompatActivity() {

    private lateinit var inputNumberView: LPInputNumberOtp
    private lateinit var inputPinNumberPin: LPInputNumberPin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_number_sample)

        inputNumberView = findViewById(R.id.input_number_view)
        inputPinNumberPin = findViewById(R.id.pin_number_view)

        inputNumberView.onShowKeyboard = { editText ->
            if (editText.isEnabled) {
                editText.requestFocus()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
            }
        }

        inputPinNumberPin.onShowKeyboard = { editText ->
            if (editText.isEnabled) {
                editText.requestFocus()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
            }
        }
    }
}