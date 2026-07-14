package com.example.alertadechuvape.utils

fun String.toDataCurta(): String {

    return try {

        val partes = split("-")

        "${partes[2]}/${partes[1]}"

    } catch (e: Exception) {

        this

    }

}