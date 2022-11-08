package com.lionparcel.commonandroidsample.alert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.alert.LPAlert
import com.lionparcel.commonandroid.alert.utils.AlertState
import com.lionparcel.commonandroid.snackbartoast.MessageType
import com.lionparcel.commonandroid.snackbartoast.showSnackbarBasicNoClose
import com.lionparcel.commonandroidsample.R

class AlertComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_component_sample)
        findViewById<LPAlert>(R.id.lp_alert).apply {
            setAlertState(AlertState.NORMAL)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
            setTextBoldSpannable("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "consectetur")
            setStartIcon()
            setEndIcon()
        }
        findViewById<LPAlert>(R.id.lp_alert_2).apply {
            setAlertState(AlertState.WARNING)
            setTextClickAndBoldSpannable("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "adipiscing elit.") {
                clickable()
            }
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
            setTextClickAndBoldSpannable("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.", clickableText = arrayOf("viverra", "consectetur")){
                clickable()
            }
            setEndIcon()
        }

        findViewById<LPAlert>(R.id.lp_alert_bloc_info_1).apply {
            setAlertState(AlertState.BLOC_INFO)
            setTextTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
            setStartIcon(isBlocInfo = true)
            setEndIcon(isBlocInfo = true)
            setEndIconClickListener {
                this.isVisible = false
            }
        }
        findViewById<LPAlert>(R.id.lp_alert_bloc_info_2).apply {
            setAlertState(AlertState.BLOC_INFO)
            setTextTitle("Vitae sed elementum lacus.")
            setTextContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nisi duis iaculis viverra quam.")
            setStartIcon(isBlocInfo = true)
        }
    }

    private fun clickable() {
        showSnackbarBasicNoClose(findViewById(R.id.llAlertParent), "This can be clicked", MessageType.SUCCESS)
    }
}