package com.lionparcel.commonandroidsample.counterinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lionparcel.commonandroid.counterinfo.LPCountdownBlocBar
import com.lionparcel.commonandroid.counterinfo.LPCountdownCircle
import com.lionparcel.commonandroid.counterinfo.LPCountdownText
import com.lionparcel.commonandroid.counterinfo.utils.CountdownTextState
import com.lionparcel.commonandroid.counterinfo.utils.CustomCountDownTimer
import com.lionparcel.commonandroidsample.R

class CounterInfoSampleActivity : AppCompatActivity() {

    companion object {
        const val MAX_DEFAULT_TIME = 30000L
    }

    private var timer: CustomCountDownTimer = initiateTimer(MAX_DEFAULT_TIME)

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
            setTextTimer("1j")
        }
        findViewById<LPCountdownBlocBar>(R.id.countdownBar).apply {
            setTextTimer("30 detik")
        }
        timer.start()
    }

    private fun initiateTimer(timeLeftInMills: Long): CustomCountDownTimer {
        return CustomCountDownTimer.getInstance(
            timeLeftInMills,
            1000,
            { onTickTimer(it/1000) }
        )
    }

    private fun onTickTimer(timeLeftInMills: Long) {
        findViewById<LPCountdownText>(R.id.ctSmallNormalNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctSmallNormalDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctMediumNormalNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctMediumNormalDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctXLNormalNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctXLNormalDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctSmallTransparantNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctSmallTransparantDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctMediumTransparantNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctMediumTransparantDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctXLTransparantNormal).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctXLTransparantDanger).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctSmallInverted).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctMediumInverted).setTime(timeLeftInMills)
        findViewById<LPCountdownText>(R.id.ctXLInverted).setTime(timeLeftInMills)
        findViewById<LPCountdownCircle>(R.id.countdownCircle).setTime(timeLeftInMills, 30.toDouble())
        findViewById<LPCountdownBlocBar>(R.id.countdownBar).setTime(timeLeftInMills, 30.toDouble())

    }
}