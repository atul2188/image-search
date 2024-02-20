package com.example.imgurimagesearch.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Constants {
    const val BASE_URL = "https://api.imgur.com/3/"

    const val CLIENT_ID = "f5a36caac30732d"

    @SuppressLint("WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    fun epochToLocalDateTime(epochTime: Long): String {
        return Instant.ofEpochSecond(epochTime)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .format(DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a"))
    }
}