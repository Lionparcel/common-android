package com.lionparcel.commonandroidsample.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lionparcel.commonandroid.loading.LPCustomRefreshSpinner
import com.lionparcel.commonandroid.loading.LPFullScreenSpinner
import com.lionparcel.commonandroidsample.R

class LoadingSpinnerDefaultSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_spinner_default_sample)
        findViewById<Button>(R.id.btnBack).setOnClickListener{
            onBackPressed()
        }

    }


}
