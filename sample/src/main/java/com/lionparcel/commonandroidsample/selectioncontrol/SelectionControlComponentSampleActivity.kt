package com.lionparcel.commonandroidsample.selectioncontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.selectioncontrol.LPRadioButton
import com.lionparcel.commonandroidsample.R

class SelectionControlComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_control_component_sample)
        findViewById<LPRadioButton>(R.id.lpRadioButton1).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton2).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton3).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton4).disableRadioButton()
    }
}