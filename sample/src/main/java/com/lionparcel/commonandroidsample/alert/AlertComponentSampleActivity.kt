package com.lionparcel.commonandroidsample.alert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.alert.LPAlert
import com.lionparcel.commonandroid.alert.utils.AlertState
import com.lionparcel.commonandroidsample.R

class AlertComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_component_sample)
        findViewById<LPAlert>(R.id.lp_alert).apply {
            setAlertState(AlertState.NORMAL)
            setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        }
        findViewById<LPAlert>(R.id.lp_alert_2).apply {
            setAlertState(AlertState.WARNING)
            setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        }
        findViewById<LPAlert>(R.id.lp_alert_3).apply {
            setAlertState(AlertState.DANGER)
            setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        }
    }
}