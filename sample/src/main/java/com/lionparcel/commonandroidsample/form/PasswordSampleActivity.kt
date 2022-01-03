package com.lionparcel.commonandroidsample.form

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.form.LPPasswordLayout
import com.lionparcel.commonandroidsample.R

class PasswordSampleActivity : AppCompatActivity() {

    private lateinit var errorHelperText: LPPasswordLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_sample)

        errorHelperText = findViewById(R.id.passwordView2)
        errorHelperText.helperEnable("Lorem pisum")
    }
}