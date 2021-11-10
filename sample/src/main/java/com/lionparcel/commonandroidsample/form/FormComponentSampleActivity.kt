package com.lionparcel.commonandroidsample.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroidsample.R

class FormComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Form Component Sample"
        setContentView(R.layout.activity_form_component_sample)

//        findViewById<TextInputLayout>(R.id.tilReceiverAddress).error = "Your error information here"
    }
}
