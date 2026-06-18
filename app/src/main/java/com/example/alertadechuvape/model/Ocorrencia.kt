package com.example.alertadechuvape.model

data class Ocorrencia(
    val tipo: String,
    val descricao: String? = null,
    val local: String? = null
)