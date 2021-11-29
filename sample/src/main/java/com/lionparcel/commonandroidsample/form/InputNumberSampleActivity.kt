package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroidsample.R
import kotlinx.android.synthetic.main.activity_input_number_sample.*


class InputNumberSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_number_sample)

        input_number_view.onShowKeyboard = { editText ->
            if (editText.isEnabled) {
                editText.requestFocus()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
            }
        }
    }
}