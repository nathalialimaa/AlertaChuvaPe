package com.example.alertadechuvape.model

object TiposOcorrencia {

    val lista = listOf(
        "Alagamento",
        "Deslizamento",
        "Chuva intensa",
        "Queda de árvore",
        "Falta de energia",
        "Via interditada"
    )

    fun emoji(tipo: String): String =
        when (tipo) {
            "Alagamento" -> "🌊"
            "Deslizamento" -> "⛰️"
            "Chuva intensa" -> "🌧️"
            "Queda de árvore" -> "🌳"
            "Falta de energia" -> "⚡"
            "Via interditada" -> "🚧"
            else -> "📍"
        }
}