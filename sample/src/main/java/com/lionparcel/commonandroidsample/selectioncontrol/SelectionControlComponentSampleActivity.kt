package com.lionparcel.commonandroidsample.selectioncontrol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.selectioncontrol.LPCheckBox
import com.lionparcel.commonandroid.selectioncontrol.LPRadioButton
import com.lionparcel.commonandroid.selectioncontrol.LPToggle
import com.lionparcel.commonandroidsample.R

class SelectionControlComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_control_component_sample)
        findViewById<LPRadioButton>(R.id.lpRadioButton1).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton2).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton3).disableRadioButton()
        findViewById<LPRadioButton>(R.id.lpRadioButton4).disableRadioButton()

        findViewById<LPCheckBox>(R.id.lpCheckBox1).disableCheckBox()
        findViewById<LPCheckBox>(R.id.lpCheckBox2).disableCheckBox()
        findViewById<LPCheckBox>(R.id.lpCheckBox3).disableCheckBox()
        findViewById<LPCheckBox>(R.id.lpCheckBox4).disableCheckBox()

        findViewById<LPToggle>(R.id.lpToggle1).disableToggle()
        findViewById<LPToggle>(R.id.lpToggle2).disableToggle()
    }
}