package com.lionparcel.commonandroidsample.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.button.LPButton
import com.lionparcel.commonandroid.loading.LPCustomLoadingDA
import com.lionparcel.commonandroid.popup.showCustomPopup
import com.lionparcel.commonandroid.snackbartoast.MessageType
import com.lionparcel.commonandroid.snackbartoast.showSnackbarBasicNoClose
import com.lionparcel.commonandroidsample.R

class LoadingCustomDASampleActivity : AppCompatActivity() {

    private val loading1: LPCustomLoadingDA by lazy {
        LPCustomLoadingDA.newInstance(content = "Silahkan Menunggu Sebentar", backCancelDisable = false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_custom_dasample)

        findViewById<LPButton>(R.id.btnLoadingDA1).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "Loading 1 Fragment",
                loading1
            )
        }

        loading1.setOnDismissListener { dismissAction() }
        findViewById<LPButton>(R.id.btnLoadingDA2).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "Loading 2 Fragment",
                LPCustomLoadingDA.newInstance(title = "Silahkan Menunggu Sebentar", illustrationImage = R.drawable.ill_badge_failed, backgroundTransparency = LPCustomLoadingDA.BACKGROUND_TRANSPARENT)
            )
        }
    }

    private fun dismissAction() {
        showSnackbarBasicNoClose(
            findViewById(R.id.loadingDASample),
            "Berhasil",
            MessageType.DEFAULT
        )
    }
}