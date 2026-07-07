package com.example.alertadechuvape.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDataHora(): String {

    val formato = SimpleDateFormat(
        "dd/MM/yyyy • HH:mm",
        Locale("pt", "BR")
    )

    return formato.format(Date(this))

}