package com.example.alertadechuvape.model

import androidx.compose.ui.graphics.Color

object TipoOcorrenciaUI {

    fun cor(tipo: String): Color {

        return when (tipo) {

            "Alagamento" -> Color(0xFF2196F3)

            "Deslizamento" -> Color(0xFF8D6E63)

            "Chuva intensa" -> Color(0xFF1565C0)

            "Queda de árvore" -> Color(0xFF43A047)

            "Falta de energia" -> Color(0xFFFFC107)

            "Via interditada" -> Color(0xFFE53935)

            else -> Color.Gray

        }

    }

}