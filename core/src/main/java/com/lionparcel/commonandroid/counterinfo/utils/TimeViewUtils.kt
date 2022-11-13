package com.lionparcel.commonandroid.counterinfo.utils

object TimeViewUtils {

    fun setCountDownTimeText(remainingSecond: Long): String {
        val time = if (remainingSecond <= 0) 0 else remainingSecond
        val minutes = time / 60
        val seconds = time % 60
        val remainingMinutesString = if (minutes < 10) "0$minutes" else minutes.toString()
        val remainingSecondsString = if (seconds < 10) "0$seconds" else seconds.toString()
        return "$remainingMinutesString : $remainingSecondsString"
    }

    fun setCountDownTimeTextWithHours(remainingSecond: Long): String {
        val time = if (remainingSecond <= 0) 0 else remainingSecond
        val hours = time / 3600
        val minutes = time / 60 % 60
        val seconds = time % 60
        val remainingHoursString = if (hours < 10) "0$hours" else hours.toString()
        val remainingMinutesString = if (minutes < 10) "0$minutes" else minutes.toString()
        val remainingSecondsString = if (seconds < 10) "0$seconds" else seconds.toString()
        return "$remainingHoursString : $remainingMinutesString : $remainingSecondsString"
    }
}