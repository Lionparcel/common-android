package com.lionparcel.commonandroid.datepicker.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val TIME_STAMP_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"
    const val TIME_ZONE_FORMAT = "ZZZZZ"
    const val COMPLETE_FORMAT = "dd MMM yyyy · HH:mm"
    const val DATE_FORMAT = "dd MMM yyyy"
    const val DATE_NUMBER_FORMAT = "yyyy-MM-dd"
    const val DATE_STRING_FORMAT = "dd-MM-yyyy"
    const val MIXPANEL_DATE_FORMAT = "MMM dd, yyyy"
    const val DATE_BIRTH_FORMAT = "dd/MM/yyyy"
    const val DAY_FORMAT = "EEEE"
    const val DATE_COMPLETE_FORMAT = "dd MMMM yyyy"
    const val TIME_FORMAT = "HH:mm"
    const val HOUR_FORMAT = "HH"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val TIME_FORMAT_UTC = "HH:mm:ss'Z'"
    const val PLUS_SEVEN_FORMAT = "+07:00"
    const val Z_FORMAT = "Z"
    private const val LOCALE_INDONESIA = "in"
    const val T_FORMAT = "T"
    private const val UTC_TIMEZONE = "UTC"
    private const val MILLIS_IN_DAY = 1000 * 60 * 60 * 24

    fun dateToString(date: Date, format: String, isLocalTimeZone: Boolean = false): String {
        val dateFormat = buildDateFormat(format)
        if (isLocalTimeZone) dateFormat.timeZone = TimeZone.getDefault()
        return dateFormat.format(date)
    }

    fun stringToDate(dateString: String, format: String): Date {
        return buildDateFormat(format).parse(dateString)
    }

    private fun buildDateFormat(format: String) = when (format) {
        TIME_STAMP_FORMAT -> buildUtcDateFormat(format)
        else -> buildSimpleDateFormat(format)
    }

    fun buildSimpleDateFormat(format: String) =
        SimpleDateFormat(format, Locale(LOCALE_INDONESIA))

    private fun buildUtcDateFormat(format: String) =
        SimpleDateFormat(format, Locale(LOCALE_INDONESIA)).apply {
            timeZone = TimeZone.getTimeZone(UTC_TIMEZONE)
        }

    fun buildGMTZeroDateFormat(format: String) =
        SimpleDateFormat(format, Locale(LOCALE_INDONESIA)).apply {
            timeZone = TimeZone.getTimeZone("GMT+0:00")
        }

    fun buildGMTDateFormat(format: String, zoneOfGMT: String) =
        SimpleDateFormat(format, Locale(LOCALE_INDONESIA)).apply {
            timeZone = TimeZone.getTimeZone("GMT$zoneOfGMT")
        }

    fun getNextDate(format: String, time: Long) : String {
        val dateFormat = SimpleDateFormat(format, Locale(LOCALE_INDONESIA))
        return dateFormat.format(time + MILLIS_IN_DAY)
    }

    fun onePackAvailable(date: Date? = null, hour: Int? = null): Boolean {
        hour?.let {
            return it < 14
        }
        val localHour = dateToString(date ?: Date(), HOUR_FORMAT).toIntOrNull() ?: 0
        return localHour < 14
    }

    fun isSameDay(dateA: Date, dateB: Date): Boolean {
        val calendarA = Calendar.getInstance()
        calendarA.time = dateA
        val calendarB = Calendar.getInstance()
        calendarB.time = dateB
        return calendarA.get(Calendar.DAY_OF_YEAR) == calendarB.get(Calendar.DAY_OF_YEAR) &&
                calendarA.get(Calendar.YEAR) == calendarB.get(Calendar.YEAR)
    }

}