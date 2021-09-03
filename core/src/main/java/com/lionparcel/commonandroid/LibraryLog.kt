package com.lionparcel.commonandroid

import android.util.Log

class LibraryLog {

    companion object {
        private const val TAG = "LibraryLog"

        fun debug(message: String) {
            Log.d(TAG, message)
        }
    }

    internal fun asd() {
        Log.d(TAG, "TEST")
    }

    fun dsa() {
        Log.d(TAG, "DSA")
    }
}