package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPPasswordLayout
import com.lionparcel.commonandroidsample.R

class PasswordSampleActivity : AppCompatActivity() {

    private lateinit var edtPasswordOld: LPPasswordLayout
    private lateinit var edtPasswordNew: LPPasswordLayout
    private lateinit var btnCheck: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_sample)

        btnCheck = findViewById(R.id.btn_check)
        edtPasswordOld = findViewById(R.id.passwordView)
        edtPasswordNew = findViewById(R.id.passwordView2)

        btnCheck.setOnClickListener {
            edtPasswordNew.helperTextShowError("Password yang anda masukkan salah")
        }
    }
}