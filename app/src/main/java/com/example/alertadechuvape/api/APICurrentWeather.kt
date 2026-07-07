package com.example.alertadechuvape.api

import com.example.alertadechuvape.model.Weather

data class APICurrentWeather(

    var location: APILocation? = null,
    var current: APICurrent? = null

)

fun APICurrentWeather.toWeather(): Weather {

    return Weather(

        //cidade = location?.name ?: "",

        descricao = current?.condition?.text ?: "",

        temperatura = current?.temp_c ?: 0.0,

        sensacao = current?.feelslike_c ?: 0.0,

        umidade = current?.humidity ?: 0,

        vento = current?.wind_kph ?: 0.0,

        icone = "https:${current?.condition?.icon}"

    )

}