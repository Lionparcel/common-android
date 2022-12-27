package com.lionparcel.commonandroidsample.modal

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.modal.LPModalDialogFragment
import com.lionparcel.commonandroid.modal.showCustomModal
import com.lionparcel.commonandroidsample.R

class ModalComponentSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modal_component_sample)
        findViewById<Button>(R.id.btnBasicTextOnly).setOnClickListener {
            showCustomModal(
                supportFragmentManager,
                "LP BASIC TEXT ONLY",
                LPModalDialogFragment.lpModalBasicTextOnly(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    cancelableTouchOutside = false
                ) { Toast.makeText(this, "Dismiss", Toast.LENGTH_SHORT).show() }
            )
        }
        findViewById<Button>(R.id.btnBasic1Button).setOnClickListener {
            showCustomModal(
                supportFragmentManager,
                "LP BASIC 1 BUTTON",
                LPModalDialogFragment.lpModalBasic1Button(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    "Prime Button"
                )
            )
        }
        findViewById<Button>(R.id.btnBasic2Button).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP BASIC 2 BUTTON",
            LPModalDialogFragment.lpModalBasic2Button(
                "Title Insert Here",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "Primary Button",
                "Second Button",
                secondaryButtonDismissAfterClick = false,
                btnPrimaryListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                btnSecondListener = { Toast.makeText(this, "Secondary", Toast.LENGTH_SHORT).show() },
                callbackOnDismissListener = { Toast.makeText(this, "Dismiss", Toast.LENGTH_SHORT).show() }
            )
        )
        }
        findViewById<Button>(R.id.btnBasic2ButtonAlt).setOnClickListener { showCustomModal(
                supportFragmentManager,
                "LP BASIC 2 BUTTON ALT",
                LPModalDialogFragment.lpModalBasic2ButtonAlt(
                    "Title Insert Here",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                    "Primary Button",
                    "Secondary Button",
                    btnPrimaryListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                    btnSecondListener = { Toast.makeText(this, "Secondary", Toast.LENGTH_SHORT).show() }
                )
            )
        }
        findViewById<Button>(R.id.btnBasicBackIcon).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP BASIC BACK ICON",
            LPModalDialogFragment.lpModalBasicBackIcon(
                "Title Insert Here",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "Click Me"
            )
        )
        }
        findViewById<Button>(R.id.btnBasicFilter).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP BASIC FILTER",
            LPModalDialogFragment.lpModalBasicFilter(
                "Title Insert Here",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "Button",
                listItem = listOf("List Item Title 1","List Item Title 2","List Item Title 3","List Item Title 4"),
                selectedItem = 1
            )
        )
        }
        findViewById<Button>(R.id.btnIllustration1Button).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP ILLUSTRATION 1 BUTTON",
            LPModalDialogFragment.lpModalIllustration1Button(
                "Title Insert Here",
                "Hello world!!! ",
                "Primary Button",
                image = R.drawable.spot_illustration,
            )
        )
        }
        findViewById<Button>(R.id.btnIllustration2Button).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP ILLUSTRATION 2 BUTTON",
            LPModalDialogFragment.lpModalIllustration2Button(
                "Title Insert Here",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "Yes",
                "No",
                btnPrimaryListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                btnSecondListener = { Toast.makeText(this, "Secondary", Toast.LENGTH_SHORT).show() },
                image = R.drawable.spot_illustration,
                cancelableTouchOutside = false
            )
        )
        }
        findViewById<Button>(R.id.btnIllustration2ButtonAlt).setOnClickListener {  showCustomModal(
            supportFragmentManager,
            "LP ILLUSTRATION 2 BUTTON ALT",
            LPModalDialogFragment.lpModalIllustration2ButtonAlt(
                "Title Insert Here",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "Primary Button",
                "Secondary Button",
                btnPrimaryListener = { Toast.makeText(this, "Primary", Toast.LENGTH_SHORT).show() },
                btnSecondListener = { Toast.makeText(this, "Secondary", Toast.LENGTH_SHORT).show() },
                image = R.drawable.spot_illustration
            )
        )
        }
    }


}
