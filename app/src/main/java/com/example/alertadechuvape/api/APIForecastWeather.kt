package com.example.alertadechuvape.api

import com.example.alertadechuvape.model.Forecast
import com.example.alertadechuvape.api.APIDay
import kotlin.collections.map

data class APIForecastWeather(

    var location: APILocation? = null,

    var current: APICurrent? = null,

    var forecast: APIForecast? = null

)

data class APIForecast(

    var forecastday: List<APIForecastDay>? = null

)

fun APIForecastWeather.toForecast(): List<Forecast> {

    return forecast?.forecastday?.map {

        Forecast(

            data = it.date ?: "",

            descricao = it.day?.condition?.text ?: "",

            temperaturaMin = it.day?.mintemp_c ?: 0.0,

            temperaturaMax = it.day?.maxtemp_c ?: 0.0,

            icone = "https:${it.day?.condition?.icon}"

        )

    } ?: emptyList()

}