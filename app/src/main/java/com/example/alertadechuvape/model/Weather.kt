package com.example.alertadechuvape.model

data class Weather(
    //val cidade: String,
    val descricao: String,
    val temperatura: Double,
    val sensacao: Double,
    val umidade: Int,
    val vento: Double,
    val icone: String,

) {

    companion object {

        val LOADING = Weather(
            //cidade = "...",
            descricao = "Carregando...",
            temperatura = 0.0,
            sensacao = 0.0,
            umidade = 0,
            vento = 0.0,
            icone = ""
        )

    }

}