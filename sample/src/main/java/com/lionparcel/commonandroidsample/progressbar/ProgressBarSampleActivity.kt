package com.lionparcel.commonandroidsample.progressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.progressbar.LPProgressBar
import com.lionparcel.commonandroidsample.R

class ProgressBarSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar_sample)

        findViewById<LPProgressBar>(R.id.pbSample1).apply {
            setProgress(100)
            setDynamicIndicatorTitle("10x")
            setFixIndicatorTitle("10x")
        }
        findViewById<LPProgressBar>(R.id.pbSample2).apply {
            setProgress(50)
            setDynamicIndicatorTitle("5x")
            setFixIndicatorTitle("10x")
        }
        findViewById<LPProgressBar>(R.id.pbSample3).apply {
            setProgress(0)
            setDynamicIndicatorTitle("0x")
            setFixIndicatorTitle("10x")
        }
        findViewById<LPProgressBar>(R.id.pbSample4).apply {
            setProgress(66)
        }
    }
}
