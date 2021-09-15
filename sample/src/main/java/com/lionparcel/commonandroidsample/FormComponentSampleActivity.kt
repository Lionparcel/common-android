package com.lionparcel.commonandroidsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputLayout

class FormComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Form Component Sample"
        setContentView(R.layout.activity_form_component_sample)

        findViewById<TextInputLayout>(R.id.tilReceiverAddress).error = "Your error information here"
    }
}