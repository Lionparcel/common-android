package com.lionparcel.commonandroidsample.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.button.LPButton
import com.lionparcel.commonandroid.form.LPCountInput
import com.lionparcel.commonandroid.form.LPListCountInput
import com.lionparcel.commonandroidsample.R

class StepperInputSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_input_sample)
        val stepperDivider: LPCountInput = findViewById(R.id.lpCountInputDivider)
        val stepperNoDivider: LPCountInput = findViewById(R.id.lpCountInputNoDivider)
        val listStepperDivider: LPListCountInput = findViewById(R.id.lpListCountDivider)
        val listStepperNoDivider: LPListCountInput = findViewById(R.id.lpListCountNoDivider)
        val btnCount: LPButton = findViewById(R.id.btnDisableCountInfo)
        stepperDivider.setCurrentQty(3)
        stepperNoDivider.setMinQtyValue(2)
        listStepperDivider.apply {
            setListTitle("List Count")
            setListAssistiveText("Divider")
        }
        listStepperNoDivider.apply {
            setListTitle("List Count")
            setListAssistiveText("No Divider")
            setCurrentQty(89)
        }
        btnCount.setOnClickListener {
            stepperDivider.isEnabled = !stepperDivider.isEnabled
            stepperNoDivider.isEnabled = !stepperNoDivider.isEnabled
            listStepperDivider.isEnabled = !listStepperDivider.isEnabled
        }
    }
}