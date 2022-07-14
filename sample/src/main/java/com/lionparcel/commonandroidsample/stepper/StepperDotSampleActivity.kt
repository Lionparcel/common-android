package com.lionparcel.commonandroidsample.stepper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.stepper.LPStepperDot
import com.lionparcel.commonandroid.stepper.utils.StepperDotPosition
import com.lionparcel.commonandroid.stepper.utils.StepperDotState
import com.lionparcel.commonandroidsample.R

class StepperDotSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_dot_sample)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_1).stepperState(StepperDotState.SUCCESS)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_1).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_2).stepperState(StepperDotState.PRIMARY)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_2).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_3).stepperState(StepperDotState.DEFAULT)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_3).stepperPosition(StepperDotPosition.LAST)


        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_4).stepperState(StepperDotState.FAILED)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_4).stepperPosition(StepperDotPosition.LAST)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_5).stepperState(StepperDotState.DEFAULT)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_5).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_6).stepperState(StepperDotState.PRIMARY)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_ver_6).stepperPosition(StepperDotPosition.NORMAL)

        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_1).stepperState(StepperDotState.SUCCESS)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_1).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_2).stepperState(StepperDotState.PRIMARY)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_2).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_3).stepperState(StepperDotState.DEFAULT)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_3).stepperPosition(StepperDotPosition.LAST)


        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_4).stepperState(StepperDotState.FAILED)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_4).stepperPosition(StepperDotPosition.LAST)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_5).stepperState(StepperDotState.DEFAULT)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_5).stepperPosition(StepperDotPosition.NORMAL)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_6).stepperState(StepperDotState.PRIMARY)
        findViewById<LPStepperDot>(R.id.lp_stepper_dot_hor_6).stepperPosition(StepperDotPosition.NORMAL)

    }
}