package com.lionparcel.commonandroid

import android.util.Log

class LogDebug {

    companion object {
        val TAG = "LionParcel"

        fun d(message: String) {
            Log.d(TAG, "message")
        }
    }
}
