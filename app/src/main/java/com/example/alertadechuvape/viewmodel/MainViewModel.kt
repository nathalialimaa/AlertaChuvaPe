package com.example.alertadechuvape.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.alertadechuvape.model.Ocorrencia
import com.google.android.gms.maps.model.LatLng

fun getOcorrencias() = List(10) { i ->

    Ocorrencia(
        tipo = "Alagamento $i",
        descricao = "Ocorrência em análise"
    )
}

class MainViewModel : ViewModel() {

    private val _ocorrencias =
        getOcorrencias().toMutableStateList()

    val ocorrencias
        get() = _ocorrencias.toList()

    fun remove(ocorrencia: Ocorrencia) {
        _ocorrencias.remove(ocorrencia)
    }

    fun add(
        tipo: String,
        local: LatLng? = null
    ) {
        _ocorrencias.add(
            Ocorrencia(
                tipo = tipo,
                local = local
            )
        )
    }
}