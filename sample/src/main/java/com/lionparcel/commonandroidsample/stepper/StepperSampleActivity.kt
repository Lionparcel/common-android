package com.lionparcel.commonandroidsample.stepper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.R

class StepperSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_component_sample)
        findViewById<Button>(R.id.btn_stepper_dot).setOnClickListener {
            startActivity(Intent(this, StepperDotSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_stepper_number).setOnClickListener {
            startActivity(Intent(this, StepperNumberSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_stepper_icon).setOnClickListener {
            startActivity(Intent(this, StepperIconSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btn_stepper_carousel).setOnClickListener {
            startActivity(Intent(this, StepperCarouselSampleActivity::class.java))
        }
    }
}