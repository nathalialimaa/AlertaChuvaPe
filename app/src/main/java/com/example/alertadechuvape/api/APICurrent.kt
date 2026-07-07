package com.example.alertadechuvape.api

data class APICurrent(

    var temp_c: Double? = null,
    var feelslike_c: Double? = null,
    var humidity: Int? = null,
    var wind_kph: Double? = null,
    var condition: APICondition? = null

)