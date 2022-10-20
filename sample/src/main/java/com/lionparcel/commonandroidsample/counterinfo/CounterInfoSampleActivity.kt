package com.lionparcel.commonandroidsample.counterinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.counterinfo.LPCountdownBlocBar
import com.lionparcel.commonandroid.counterinfo.LPCountdownCircle
import com.lionparcel.commonandroid.counterinfo.LPCountdownText
import com.lionparcel.commonandroid.counterinfo.utils.CountdownTextState
import com.lionparcel.commonandroidsample.R

class CounterInfoSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_info_sample)
        findViewById<LPCountdownText>(R.id.ctSmallNormalNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctSmallNormalDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownText>(R.id.ctMediumNormalNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctMediumNormalDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownText>(R.id.ctXLNormalNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctXLNormalDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownText>(R.id.ctSmallTransparantNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctSmallTransparantDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownText>(R.id.ctMediumTransparantNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctMediumTransparantDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownText>(R.id.ctXLTransparantNormal).apply {
            setCountdownState(CountdownTextState.NORMAL)
        }
        findViewById<LPCountdownText>(R.id.ctXLTransparantDanger).apply {
            setCountdownState(CountdownTextState.DANGER)
        }
        findViewById<LPCountdownCircle>(R.id.countdownCircle).apply {
            getCircleTimer().apply {
                progress = 50
                max = 100
            }
        }
        findViewById<LPCountdownBlocBar>(R.id.countdownBar).apply {
            getBarTimer().apply {
                progress = 50
                max = 100
            }
        }
    }
}