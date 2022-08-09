package com.lionparcel.commonandroidsample.stepper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.stepper.LPStepperIcon
import com.lionparcel.commonandroid.stepper.utils.StepperIconState
import com.lionparcel.commonandroidsample.R

class StepperIconSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepper_icon_sample)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_1).firstIcon(StepperIconState.WARNING)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_1).secondIcon(StepperIconState.WARNING)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_1).thirdIcon(StepperIconState.WARNING)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_1).fourthIcon(StepperIconState.WARNING)


        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_2).firstIcon(StepperIconState.SUCCESS)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_2).secondIcon(StepperIconState.FAILED)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_2).thirdIcon(StepperIconState.WARNING)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_2).fourthIcon(StepperIconState.ACTIVE)


        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_3).firstIcon(StepperIconState.INACTIVE)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_3).secondIcon(StepperIconState.INACTIVE)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_3).thirdIcon(StepperIconState.INACTIVE)
        findViewById<LPStepperIcon>(R.id.lp_stepper_icon_3).fourthIcon(StepperIconState.INACTIVE)
    }
}
