package com.example.alertadechuvape.model

import com.google.android.gms.maps.model.BitmapDescriptorFactory

object TipoOcorrenciaMapa {

    fun cor(tipo: String): Float {

        return when (tipo) {

            "Alagamento" ->
                BitmapDescriptorFactory.HUE_AZURE

            "Deslizamento" ->
                BitmapDescriptorFactory.HUE_ORANGE

            "Chuva intensa" ->
                BitmapDescriptorFactory.HUE_BLUE

            "Queda de árvore" ->
                BitmapDescriptorFactory.HUE_GREEN

            "Falta de energia" ->
                BitmapDescriptorFactory.HUE_YELLOW

            "Via interditada" ->
                BitmapDescriptorFactory.HUE_RED

            else ->
                BitmapDescriptorFactory.HUE_VIOLET

        }

    }

}