package com.lionparcel.commonandroidsample.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import com.lionparcel.commonandroid.banner.showBannerOneButton
import com.lionparcel.commonandroid.banner.showBannerOneButtonWithIcon
import com.lionparcel.commonandroid.banner.showBannerTwoButton
import com.lionparcel.commonandroid.banner.showBannerTwoButtonWithIcon
import com.lionparcel.commonandroidsample.R

class BannerComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_component_sample)
        val parent = findViewById<LinearLayout>(R.id.bannerParent)
        findViewById<Button>(R.id.btnBannerOneButton).setOnClickListener {
            showBannerOneButton(
                parent,
                getString(R.string.general_short_message),
                getString(R.string.general_button_finish),
                actionListener = { it.dismiss() },
                callbackOnDismiss = {}
            )
        }

        findViewById<Button>(R.id.btnBannerOneButtonWithIcon).setOnClickListener {
            showBannerOneButtonWithIcon(
                parent,
                getString(R.string.general_short_message),
                getString(R.string.general_button_finish),
                actionListener = { it.dismiss() },
                callbackOnDismiss = {}
            )
        }

        findViewById<Button>(R.id.btnBannerTwoButton).setOnClickListener {
            showBannerTwoButton(
                parent,
                getString(R.string.general_short_message),
                getString(R.string.general_button_finish),
                getString(R.string.general_button_next),
                rightActionListener = { it.dismiss() },
                callbackOnDismiss = {}
            )
        }

        findViewById<Button>(R.id.btnBannerTwoButtonWithIcon).setOnClickListener {
            showBannerTwoButtonWithIcon(
                parent,
                getString(R.string.general_message),
                getString(R.string.general_button_next),
                getString(R.string.general_button_finish),
                leftActionListener = { it.dismiss() },
                callbackOnDismiss = {},
                duration = Snackbar.LENGTH_LONG
            )
        }
    }
}