package com.lionparcel.commonandroidsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.form.FormComponentSampleActivity
import com.lionparcel.commonandroidsample.loading.LoadingComponentSampleActivity
import com.lionparcel.commonandroidsample.modal.ModalComponentSampleActivity

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
    }
}
