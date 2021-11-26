package com.lionparcel.commonandroidsample.form

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroidsample.R
import kotlinx.android.synthetic.main.activity_input_number_sample.*


class InputNumberSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_number_sample)

//        input_number_view.setOnFinishListener {
//            Log.i("Activity", it)
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        }
//
//        input_number_view.setOnCharacterUpdatedListener {
//            if(it) continue_button.isEnabled = it
//        }

        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        val width = displayMetrics.widthPixels

//        input_number_view.fitToWidth(width)

        input_number_view.onShowKeyboard = { editText ->
            if (editText.isEnabled) {
                editText.requestFocus()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
            }
        }


//        continue_button.setOnClickListener {
//            Toast.makeText(this, input_number_view.getStringFromFields(), Toast.LENGTH_LONG).show()
//        }
    }
}