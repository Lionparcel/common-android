package com.lionparcel.commonandroidsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroidsample.loading.LoadingComponentSampleActivity

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
    }
}
