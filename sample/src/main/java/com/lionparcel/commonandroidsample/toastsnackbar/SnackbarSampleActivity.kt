package com.lionparcel.commonandroidsample.toastsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import com.lionparcel.commonandroid.snackbartoast.*
import com.lionparcel.commonandroidsample.R

class SnackbarSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar_sample)
        val parent = findViewById<ScrollView>(R.id.parent)
        findViewById<Button>(R.id.btnSnackbarDefaultBasicNoClose).setOnClickListener {
            showSnackbarBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultSmallIconNoClose).setOnClickListener {
            showSnackbarSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultLargeIconNoClose).setOnClickListener {
            showSnackbarLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultButtonText).setOnClickListener {
            showSnackbarButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultSmallIconButtonText).setOnClickListener {
            showSnackbarSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultLargeIconButtonText).setOnClickListener {
            showSnackbarLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultBasicWithClose).setOnClickListener {
            showSnackbarBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultSmallIconWithClose).setOnClickListener {
            showSnackbarSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarDefaultLargeIconWithClose).setOnClickListener {
            showSnackbarLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessBasicNoClose).setOnClickListener {
            showSnackbarBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessSmallIconNoClose).setOnClickListener {
            showSnackbarSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessLargeIconNoClose).setOnClickListener {
            showSnackbarLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessBasicButtonText).setOnClickListener {
            showSnackbarButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessSmallIconButtonText).setOnClickListener {
            showSnackbarSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessLargeIconButtonText).setOnClickListener {
            showSnackbarLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessBasicWithClose).setOnClickListener {
            showSnackbarBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessSmallIconWithClose).setOnClickListener {
            showSnackbarSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnSnackbarSuccessLargeIconWithClose).setOnClickListener {
            showSnackbarLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        //

        findViewById<Button>(R.id.btnSnackbarErrorBasicNoClose).setOnClickListener {
            showSnackbarBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorSmallIconNoClose).setOnClickListener {
            showSnackbarSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorLargeIconNoClose).setOnClickListener {
            showSnackbarLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorBasicButtonText).setOnClickListener {
            showSnackbarButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorSmallIconButtonText).setOnClickListener {
            showSnackbarSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorLargeIconButtonText).setOnClickListener {
            showSnackbarLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorBasicWithClose).setOnClickListener {
            showSnackbarBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorSmallIconWithClose).setOnClickListener {
            showSnackbarSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnSnackbarErrorLargeIconWithClose).setOnClickListener {
            showSnackbarLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
    }
}
