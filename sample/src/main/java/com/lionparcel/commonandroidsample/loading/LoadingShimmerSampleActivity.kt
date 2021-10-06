package com.lionparcel.commonandroidsample.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.lionparcel.commonandroid.loading.LPCustomRefreshSpinner
import com.lionparcel.commonandroid.loading.LPFullScreenSpinner
import com.lionparcel.commonandroidsample.R

class LoadingShimmerSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_shimmer_sample)
        val type = intent.getIntExtra("TYPE", 1)
        findViewById<ConstraintLayout>(R.id.layoutShimmerNormal).isVisible = type == 1
        findViewById<ConstraintLayout>(R.id.layoutShimmerLarge).isVisible = type == 2
        findViewById<Button>(R.id.btnBack).setOnClickListener{
            onBackPressed()
        }

    }


}
