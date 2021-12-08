package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lionparcel.commonandroidsample.R

class PasswordSampleActivity : AppCompatActivity() {

    private lateinit var edtPassword: TextInputEditText
    private lateinit var edtPasswordError: TextInputEditText
    private lateinit var ivClear: ImageView
    private lateinit var ivClearError: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_sample)

        edtPassword = findViewById(R.id.edtPassword)
        edtPasswordError = findViewById(R.id.edtPasswordError)
        ivClear = findViewById(R.id.ivClear)
        ivClearError = findViewById(R.id.ivClearError)

        findViewById<TextInputLayout>(R.id.txtPasswordError).error = "Password yang anda masukkan salah"

        edtPassword.addTextChangedListener {
            if (!edtPassword.text.isNullOrBlank()){
                ivClear.visibility = View.VISIBLE
                ivClear.setOnClickListener {
                    edtPassword.text?.clear()
                }
            } else{
                ivClear.visibility = View.INVISIBLE
            }
        }

       edtPasswordError.addTextChangedListener {
           if (!edtPasswordError.text.isNullOrBlank()){
               ivClearError.visibility = View.VISIBLE
               ivClearError.setOnClickListener {
                   edtPasswordError.text?.clear()
               }
           } else{
               ivClearError.visibility = View.INVISIBLE
           }
        }

    }
}