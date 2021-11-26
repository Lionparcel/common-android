package com.lionparcel.commonandroidsample.toastsnackbar

import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.snackbartoast.*
import com.lionparcel.commonandroidsample.R

class ToastSnackbarSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_snackbar_sample)
        val parent = findViewById<ScrollView>(R.id.parent)
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
        findViewById<Button>(R.id.btnToastDefaultBasicWithClose).setOnClickListener {
            showToastDefaultBasicWithClose(
                parent,
                getString(R.string.general_message)
            )
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconWithClose).setOnClickListener {
            showToastDefaultSmallIconWithClose(
                parent,
                getString(R.string.general_message)
            )
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconWithClose).setOnClickListener {
            showToastDefaultLargeIconWithClose(
                parent,
                getString(R.string.general_message)
            )
        }
    }
}
