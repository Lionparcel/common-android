package com.lionparcel.commonandroid.counterinfo.utils

import android.os.CountDownTimer

class CustomCountDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    private var onTickTimer: ((Long) -> Unit)? = null,
    private var onFinishTimer: (() -> Unit)? = null
) : CountDownTimer(millisInFuture, countDownInterval) {

    companion object {

        fun getInstance(
            millisInFuture: Long,
            countDownInterval: Long,
            onTickAction: ((Long) -> Unit)? = null,
            onFinishAction: (() -> Unit)? = null
        ) = CustomCountDownTimer(millisInFuture, countDownInterval, onTickAction, onFinishAction)
    }

    override fun onFinish() {
        onFinishTimer?.invoke()
    }

    override fun onTick(millisUntilFinished: Long) {
        onTickTimer?.invoke(millisUntilFinished)
    }

}