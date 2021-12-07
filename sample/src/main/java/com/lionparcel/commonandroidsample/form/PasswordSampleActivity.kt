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
    private lateinit var ivClear2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_sample)

        edtPassword = findViewById(R.id.edtPassword)
        edtPasswordError = findViewById(R.id.edtPasswordError)
        ivClear = findViewById(R.id.ivClear)
        ivClear2 = findViewById(R.id.ivClear2)

        findViewById<TextInputLayout>(R.id.txtPasswordError).error = "Password yang anda masukkan salah"

        edtPassword.addTextChangedListener {
            if (!edtPassword.text.isNullOrBlank()){
                ivClear.visibility = View.VISIBLE
                ivClear.setOnClickListener {
                    edtPassword.text?.clear()
                }
            } else{
                ivClear2.visibility = View.INVISIBLE
            }
        }

       edtPasswordError.addTextChangedListener {
           if (!edtPasswordError.text.isNullOrBlank()){
               ivClear2.visibility = View.VISIBLE
               ivClear2.setOnClickListener {
                   edtPasswordError.text?.clear()
               }
           } else{
               ivClear2.visibility = View.INVISIBLE
           }
        }

    }
}