package com.canker.core.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun dateDiff(date: String): Long {
        try {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            val formattedDate = format.parse(date)
            return Date().time - formattedDate.time

        } catch (e: Exception) {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formattedDate = format.parse(date)
            return Date().time - formattedDate.time
        }
    }

    @Throws(ParseException::class)
    fun dateDiff2(date: String): String {
        val inputFormatString = "yyyy-MM-dd'T'hh:mm:ss"
        val inputDateFormat = SimpleDateFormat(inputFormatString, Locale.getDefault())
        val inputDate = inputDateFormat.parse(date)
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return outputFormat.format(inputDate)
    }


    private fun monthName(month: Int): String {
        return when (month) {
            0 -> "Ocak"
            1 -> "Şubat"
            2 -> "Mart"
            3 -> "Nisan"
            4 -> "Mayıs"
            5 -> "Haziran"
            6 -> "Temmuz"
            7 -> "Ağustos"
            8 -> "Eylül"
            9 -> "Ekim"
            10 -> "Kasım"
            11 -> "Aralık"
            else -> ""
        }
    }

    fun differencesInDays(today: String, thatDay: String): Long {
        val diff = java.lang.Long.parseLong(today) - java.lang.Long.parseLong(thatDay)
        return diff / (24 * 60 * 60 * 1000)
    }

}