package com.lionparcel.commonandroidsample.toastsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.lionparcel.commonandroid.snackbartoast.*
import com.lionparcel.commonandroidsample.R

class ToastSnackbarSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_snackbar_sample)
        val parent = findViewById<LinearLayout>(R.id.parent)
        findViewById<Button>(R.id.btnToastDefaultBasicNoClose).setOnClickListener {
            showToastDefaultBasicNoClose(parent, getString(R.string.general_message))
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconNoClose).setOnClickListener {
            showToastDefaultSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle
            )
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconNoClose).setOnClickListener {
            showToastDefaultLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle
            )
        }
        findViewById<Button>(R.id.btnToastDefaultButtonText).setOnClickListener {
            showToastDefaultButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {})
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconButtonText).setOnClickListener {
            showToastDefaultSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {})
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconButtonText).setOnClickListener {
            showToastDefaultLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {})
        }
    }
}
