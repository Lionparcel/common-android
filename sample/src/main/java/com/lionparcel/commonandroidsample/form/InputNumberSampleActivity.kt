package com.lionparcel.commonandroidsample.form

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroidsample.R
import kotlinx.android.synthetic.main.activity_input_number_sample.*


class InputNumberSampleActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_number_sample)

        input_number_view.setOnFinishListener {
            Log.i("Activity", it)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        input_number_view.setOnCharacterUpdatedListener {
            if(it)
                Log.i("Activity", "The view is filled")
            else
                Log.i("Activity", "The view is NOT Filled")
            continue_button.isEnabled = it
        }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        input_number_view.fitToWidth(width)

        continue_button.setOnClickListener {
            Toast.makeText(this, input_number_view.getStringFromFields(), Toast.LENGTH_LONG).show()
        }
    }
}