package com.lionparcel.commonandroidsample.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroidsample.R

class FormComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Form Component Sample"
        setContentView(R.layout.activity_form_component_sample)

        findViewById<TextInputLayout>(R.id.tilReceiverAddress).error = "Your error information here"

        findViewById<Button>(R.id.btn_input_number).setOnClickListener {
            startActivity(Intent(this, InputNumberSampleActivity::class.java ))
        }

        findViewById<Button>(R.id.btn_password_sample).setOnClickListener {
            startActivity(Intent(this, PasswordSampleActivity::class.java))
        }

        findViewById<Button>(R.id.btn_signature_form_sample).setOnClickListener {
            startActivity(Intent(this, SignatureFormSampleActivity::class.java))
        }

    }
}
