package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPPasswordLayout
import com.lionparcel.commonandroidsample.R

class PasswordSampleActivity : AppCompatActivity() {

    private lateinit var edtPasswordView: LPPasswordLayout
    private lateinit var edtPasswordDisable: LPPasswordLayout
    private lateinit var edtPasswordError: LPPasswordLayout
    private lateinit var btnCheck: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_sample)

        edtPasswordView = findViewById(R.id.passwordView)
        edtPasswordDisable = findViewById(R.id.disablePasswordView)
        edtPasswordError = findViewById(R.id.errorPasswordView)
        btnCheck = findViewById(R.id.btn_check)

        edtPasswordDisable.disablePassword()

        btnCheck.setOnClickListener {
            edtPasswordError.showHelperTextError("Password yang anda masukkan salah")
        }
    }
}