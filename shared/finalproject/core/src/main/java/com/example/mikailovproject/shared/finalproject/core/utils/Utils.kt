package com.example.mikailovproject.shared.finalproject.core.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val BASE_URL = "https://shiftlab.cft.ru:7777/"
    const val AUTH_TOKEN = "AUTH_TOKEN"

    fun convertDateTimeToReadableFormat(dateTime: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy | HH:mm:ss")

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val timeZone = TimeZone.getDefault()
        outputFormat.timeZone = timeZone

        val date: Date = inputFormat.parse(dateTime) as Date
        return outputFormat.format(date)
    }
}