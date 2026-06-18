package com.example.alertadechuvape.model
import com.google.android.gms.maps.model.LatLng

data class Ocorrencia(
    val tipo: String,
    val descricao: String? = null,
    val local: LatLng? = null
)