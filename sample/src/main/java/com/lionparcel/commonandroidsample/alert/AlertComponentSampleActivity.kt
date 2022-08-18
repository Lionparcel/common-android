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
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
            setSemiBoldSpannable("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "consectetur")
            setStartIcon()
            setEndIcon()
        }
        findViewById<LPAlert>(R.id.lp_alert_2).apply {
            setAlertState(AlertState.WARNING)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
            setEndIcon()
        }
        findViewById<LPAlert>(R.id.lp_alert_3).apply {
            setAlertState(AlertState.DANGER)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        }
        findViewById<LPAlert>(R.id.lp_alert_small_1).apply {
            setAlertState(AlertState.NORMAL)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
        }
        findViewById<LPAlert>(R.id.lp_alert_small_2).apply {
            setAlertState(AlertState.WARNING)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
            setStartIcon()
        }
        findViewById<LPAlert>(R.id.lp_alert_small_3).apply {
            setAlertState(AlertState.DANGER)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
            setEndIcon()
        }

        findViewById<LPAlert>(R.id.lp_alert_title_1).apply {
            setAlertState(AlertState.NORMAL)
            setTextTitle("Vitae sed elementum lacus.")
            setTextContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
        }
        findViewById<LPAlert>(R.id.lp_alert_title_2).apply {
            setAlertState(AlertState.WARNING)
            setTextTitle("Vitae sed elementum lacus.")
            setTextContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
            setStartIcon()
            setEndIcon()
        }
        findViewById<LPAlert>(R.id.lp_alert_title_3).apply {
            setAlertState(AlertState.DANGER)
            setTextTitle("Vitae sed elementum lacus.")
            setTextContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
            setSemiBoldSpannable("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.", "adipiscing")
            setEndIcon()
        }
    }
}