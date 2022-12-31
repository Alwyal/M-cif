package com.farhanrv.catatankeuangan.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateConverters {
    fun convertLongToTime (time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }
}