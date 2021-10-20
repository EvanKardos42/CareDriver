package com.enigmastudios.caredrivers.utils

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        private val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        fun getDateFromString(utcString: String):String {

            val value = utcFormat.parse(utcString)
            val dateFormat = SimpleDateFormat("E M/d", Locale.getDefault())
            return dateFormat.format(value).toString()
        }

        fun getTimeFromString(utcString: String):String {

            val value = utcFormat.parse(utcString)
            val dateFormat = SimpleDateFormat("KK:mma", Locale.getDefault())
            return dateFormat.format(value).toString()
        }
    }
}