package com.lionparcel.commonandroidsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroidsample.button.ButtonSampleActivity
import com.lionparcel.commonandroidsample.emptystate.EmptyStateSampleActivity
import com.lionparcel.commonandroidsample.form.FormComponentSampleActivity
import com.lionparcel.commonandroidsample.header.HeaderSampleActivity
import com.lionparcel.commonandroidsample.loading.LoadingComponentSampleActivity
import com.lionparcel.commonandroidsample.modal.ModalComponentSampleActivity
import com.lionparcel.commonandroidsample.popup.PopupComponentSampleActivity
import com.lionparcel.commonandroidsample.toastsnackbar.SnackbarSampleActivity
import com.lionparcel.commonandroidsample.toastsnackbar.ToastSampleActivity
import com.lionparcel.commonandroidsample.walktrough.WalkThroughSampleActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_to_formcomponentsampleactivity).setOnClickListener {
            startActivity(Intent(this, FormComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_loadingcomponentsampleactivity).setOnClickListener {
            startActivity(Intent(this, LoadingComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_modalComponentSampleactivity).setOnClickListener {
            startActivity(Intent(this, ModalComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_walkthroughsampleactivity).setOnClickListener {
            startActivity(Intent(this, WalkThroughSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_toastSampleActivity).setOnClickListener {
            startActivity(Intent(this, ToastSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_snackbarSampleActivity).setOnClickListener {
            startActivity(Intent(this, SnackbarSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_buttonSampleActivity).setOnClickListener {
            startActivity(Intent(this, ButtonSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_popupSampleActivity).setOnClickListener {
            startActivity(Intent(this, PopupComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_popupSampleActivity).setOnClickListener {
            startActivity(Intent(this, PopupComponentSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_headerSampleActivity).setOnClickListener {
            startActivity(Intent(this, HeaderSampleActivity::class.java))
        }
        findViewById<Button>(R.id.button_to_emptystateSampleActivity).setOnClickListener {
            startActivity(Intent(this, EmptyStateSampleActivity::class.java))
        }
    }
}
