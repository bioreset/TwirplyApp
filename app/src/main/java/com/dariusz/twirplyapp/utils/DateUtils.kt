package com.dariusz.twirplyapp.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

object DateUtils {

    @SuppressLint("NewApi")
    fun formatDate(dateInput: String): String? {
        val parsedDate = LocalDateTime.parse(dateInput, DateTimeFormatter.ISO_DATE_TIME)
        return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun countElapsedTime(dateOfCreation: String) =
        getDuration(now(), LocalDateTime.parse(dateOfCreation, DateTimeFormatter.ISO_DATE_TIME))

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDuration(d1: LocalDateTime, d2: LocalDateTime): String {
        var diff = Duration.between(d1, d2)
        val days = diff.toDays()
        diff = diff.minusDays(days)
        val hours = diff.toHours()
        diff = diff.minusHours(hours)
        val minutes = diff.toMinutes()
        diff = diff.minusMinutes(minutes)
        val seconds = diff.toMillis()
        val formattedDiff = StringBuilder()
        when {
            days != 0L -> {
                if (days == 1L) {
                    formattedDiff.append("${days * -1} day")
                } else {
                    formattedDiff.append("${days * -1} days")
                }
            }
            hours != 0L -> {
                if (hours == 1L) {
                    formattedDiff.append("${hours * -1} hour")
                } else {
                    formattedDiff.append("${hours * -1} hours")
                }
            }
            minutes != 0L -> {
                if (minutes == 1L) {
                    formattedDiff.append("${minutes * -1} minute")
                } else {
                    formattedDiff.append("${minutes * -1} minutes")
                }
            }
            seconds != 0L -> {
                if (seconds == 1L) {
                    formattedDiff.append("${seconds * -1} second")
                } else {
                    formattedDiff.append("${seconds * -1} seconds")
                }
            }
        }
        return formattedDiff.toString()
    }

}