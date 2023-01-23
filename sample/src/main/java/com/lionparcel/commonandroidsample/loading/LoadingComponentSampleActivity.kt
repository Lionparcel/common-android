package com.lionparcel.commonandroidsample.loading

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lionparcel.commonandroid.loading.LPFullScreenSpinner
import com.lionparcel.commonandroid.loading.LPSpinnerLoadingPage
import com.lionparcel.commonandroidsample.R

class LoadingComponentSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_component_sample)
        findViewById<Button>(R.id.btnLoading1).setOnClickListener {
            startActivity(Intent(this, LoadingSpinnerDefaultSampleActivity::class.java))
        }
        findViewById<Button>(R.id.btnLoading2).setOnClickListener {
            startActivity(Intent(this, LoadingShimmerSampleActivity::class.java).putExtra("TYPE", 1))
        }
        findViewById<Button>(R.id.btnLoading3).setOnClickListener {
            startActivity(Intent(this, LoadingShimmerSampleActivity::class.java).putExtra("TYPE", 2))
        }
        findViewById<Button>(R.id.btnLoading4).setOnClickListener {
            LPFullScreenSpinner.newInstance(this).show()
        }
        findViewById<Button>(R.id.btnLoading5).setOnClickListener {
            findViewById<LPSpinnerLoadingPage>(R.id.loadingSpinner).apply {
                visibility = View.VISIBLE
            }
        }
        findViewById<Button>(R.id.btnLoading6).setOnClickListener {
            startActivity(Intent(this, LoadingCustomDASampleActivity::class.java))
        }
    }


}
