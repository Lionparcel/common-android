package com.lionparcel.commonandroidsample.popup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.popup.LPPopupDialogFragment
import com.lionparcel.commonandroid.popup.showCustomPopup
import com.lionparcel.commonandroidsample.MainActivity
import com.lionparcel.commonandroidsample.R

class PopupComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_component_sample)
        findViewById<Button>(R.id.btnTextOnly).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ONLY",
                LPPopupDialogFragment.lpPopupTextOnly(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                )
            )
        }
        findViewById<Button>(R.id.btnTextIllustration).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ONLY",
                LPPopupDialogFragment.lpPopupTextIllustration(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    R.drawable.ill_badge_success
                )
            )
        }
        findViewById<Button>(R.id.btnTextButton).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ONLY",
                LPPopupDialogFragment.lpPopupTextButton(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    "Lion Parcel",
                    "Lion Parcel",
                    {startActivity(Intent(this, MainActivity::class.java))}
                )
            )
        }
        findViewById<Button>(R.id.btntextButtonIllustration).setOnClickListener {
            showCustomPopup(
                supportFragmentManager,
                "LP TEXT ONLY",
                LPPopupDialogFragment.lpPopupTextButtonIllustration(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    R.drawable.ill_badge_failed,
                    "Primary Button",
                    "Secondary Button"
                )
            )
        }
    }
}