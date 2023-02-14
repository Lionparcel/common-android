package com.lionparcel.commonandroidsample.popup

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.popup.LPPopupDialogFragment
import com.lionparcel.commonandroid.popup.showCustomPopup
import com.lionparcel.commonandroidsample.R

class PopupComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_component_sample)
        findViewById<Button>(R.id.btnTextOnly).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ONLY",
                LPPopupDialogFragment.newPopupDialog(
                    title = "Title Insert Here",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    cancelableTouchOutSide = false
                )
            )
        }
        findViewById<Button>(R.id.btnTextIllustration).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ILLUSTRATION",
                LPPopupDialogFragment.newPopupDialog(
                    title = "Title Insert Here",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    image = R.drawable.ill_badge_success
                )
            )
        }
        findViewById<Button>(R.id.btnTextButton).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT BUTTON",
                LPPopupDialogFragment.newPopupDialog(
                    title = "Title Insert Here",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    primaryButtonText = "Lion Parcel",
                    secondaryButtonText = "Lion Parcel",
                    dismissAfterClickButtonSecondary = false,
                    primaryButtonListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                    secondaryButtonListener = { Toast.makeText(this, "Secondary", Toast.LENGTH_SHORT).show() },
                    callbackOnDismiss = { Toast.makeText(this, "Dismiss", Toast.LENGTH_SHORT).show() }
                )
            )
        }
        findViewById<Button>(R.id.btntextButtonIllustration).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT BUTTON ILLUSTRATION",
                LPPopupDialogFragment.newPopupDialog(
                    title = "Title Insert Here",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    image = R.drawable.ill_badge_failed,
                    primaryButtonText = "Primary Button",
                    secondaryButtonText = "Secondary Button",
                    primaryButtonListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                )
            )
        }
    }
}
