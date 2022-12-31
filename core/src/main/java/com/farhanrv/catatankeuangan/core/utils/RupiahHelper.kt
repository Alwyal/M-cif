package com.farhanrv.catatankeuangan.core.utils

import java.text.NumberFormat
import java.util.*

object RupiahHelper {
    fun rupiahFormatter(rupiahInt: Int): String {
        val COUNTRY = "ID"
        val LANGUAGE = "id"
        val format = NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(rupiahInt)

        return format.format(1000000)
    }
}