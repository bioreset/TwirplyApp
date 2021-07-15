package com.dariusz.twirplyapp.utils

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    @SuppressLint("NewApi")
    fun formatDate(dateInput: String): String? {
        val parsedDate = LocalDateTime.parse(dateInput, DateTimeFormatter.ISO_DATE_TIME)
        return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
    }

}