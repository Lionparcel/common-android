package com.lionparcel.commonandroid

import android.util.Log

class LogDebug {

    companion object {
        private const val TAG = "LIONPARCEL"

        fun d(message: String) {
            Log.d(TAG, message)
        }
    }
}
