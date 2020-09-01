package com.example.base.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * created by jump on 2020/8/24
 * describe:
 */
object TimeDateUtils {

    fun getFormedDateByString(date: Long, formatStr: String?): String? {
        var format = ""
        val timeZone = TimeZone.getTimeZone("GMT+8")
        val simpleDateFormat = SimpleDateFormat(formatStr)
        simpleDateFormat.timeZone=timeZone
        try {
            format = simpleDateFormat.format(Date(date))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return format
    }

    fun getMinuteSeconds(secondsMatchStart: Int): String {
        val seconds: String
        val minutes: String
        val intSeconds = secondsMatchStart % 60
        val intMinutes = secondsMatchStart / 60
        seconds = if (intSeconds < 10) {
            "0$intSeconds"
        } else {
            intSeconds.toString()
        }
        minutes = if (intMinutes < 10) {
            "0$intMinutes"
        } else {
            intMinutes.toString()
        }
//        return "$minutes:$seconds"
        return "$minutes\'"
    }
}