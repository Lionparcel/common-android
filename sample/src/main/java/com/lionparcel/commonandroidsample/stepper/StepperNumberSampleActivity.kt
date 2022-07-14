package com.lionparcel.commonandroidsample.stepper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.stepper.LPStepperNumber
import com.lionparcel.commonandroid.stepper.utils.StepperNumberState
import com.lionparcel.commonandroidsample.R

class StepperNumberSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_number_sample)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_1).firstStepper(StepperNumberState.FINISHED)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_1).secondStepper(StepperNumberState.ACTIVE)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_1).thirdStepper(StepperNumberState.INACTIVE)


        findViewById<LPStepperNumber>(R.id.lp_stepper_number_2).firstStepper(StepperNumberState.FINISHED)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_2).secondStepper(StepperNumberState.FINISHED)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_2).thirdStepper(StepperNumberState.FAILED)

        findViewById<LPStepperNumber>(R.id.lp_stepper_number_3).setTitleAndSubtitle(firstTitle = "Penambahan", firstSubtitle = "Kalim paket akan ditambahkan")

        findViewById<LPStepperNumber>(R.id.lp_stepper_number_4).firstStepper(StepperNumberState.ACTIVE)
        findViewById<LPStepperNumber>(R.id.lp_stepper_number_4).secondStepper(StepperNumberState.FAILED)
    }
}