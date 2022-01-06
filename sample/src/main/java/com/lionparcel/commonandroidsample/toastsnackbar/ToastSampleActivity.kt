package com.lionparcel.commonandroidsample.toastsnackbar

import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.snackbartoast.*
import com.lionparcel.commonandroidsample.R

class ToastSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_sample)
        val parent = findViewById<ScrollView>(R.id.parent)
        findViewById<Button>(R.id.btnToastDefaultBasicNoClose).setOnClickListener {
            showToastBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconNoClose).setOnClickListener {
            showToastSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconNoClose).setOnClickListener {
            showToastLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultButtonText).setOnClickListener {
            showToastButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconButtonText).setOnClickListener {
            showToastSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconButtonText).setOnClickListener {
            showToastLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultBasicWithClose).setOnClickListener {
            showToastBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultSmallIconWithClose).setOnClickListener {
            showToastSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastDefaultLargeIconWithClose).setOnClickListener {
            showToastLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.DEFAULT
            )
        }
        findViewById<Button>(R.id.btnToastSuccessBasicNoClose).setOnClickListener {
            showToastBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessSmallIconNoClose).setOnClickListener {
            showToastSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessLargeIconNoClose).setOnClickListener {
            showToastLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessBasicButtonText).setOnClickListener {
            showToastButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessSmallIconButtonText).setOnClickListener {
            showToastSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessLargeIconButtonText).setOnClickListener {
            showToastLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessBasicWithClose).setOnClickListener {
            showToastBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessSmallIconWithClose).setOnClickListener {
            showToastSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        findViewById<Button>(R.id.btnToastSuccessLargeIconWithClose).setOnClickListener {
            showToastLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.SUCCESS
            )
        }
        //

        findViewById<Button>(R.id.btnToastErrorBasicNoClose).setOnClickListener {
            showToastBasicNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorSmallIconNoClose).setOnClickListener {
            showToastSmallIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorLargeIconNoClose).setOnClickListener {
            showToastLargeIconNoClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorBasicButtonText).setOnClickListener {
            showToastButtonText(
                parent,
                getString(R.string.general_message),
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorSmallIconButtonText).setOnClickListener {
            showToastSmallIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_check_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorLargeIconButtonText).setOnClickListener {
            showToastLargeIconButtonText(
                parent,
                getString(R.string.general_message),
                R.drawable.ics_f_info_circle,
                "Label Text",
                callbackMessageButton = {},
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorBasicWithClose).setOnClickListener {
            showToastBasicWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorSmallIconWithClose).setOnClickListener {
            showToastSmallIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
        findViewById<Button>(R.id.btnToastErrorLargeIconWithClose).setOnClickListener {
            showToastLargeIconWithClose(
                parent,
                getString(R.string.general_message),
                messageType = MessageType.ERROR
            )
        }
    }
}
