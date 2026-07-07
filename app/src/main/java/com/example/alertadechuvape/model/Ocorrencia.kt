package com.example.alertadechuvape.model
import com.google.android.gms.maps.model.LatLng

data class Ocorrencia(
    val id: String = "",
    val tipo: String,
    val cidade: String? = null,
    val descricao: String? = null,
    val local: LatLng? = null,
    val dataHora: Long = System.currentTimeMillis()
)