package com.example.alertadechuvape.api

data class APIDay(

    var maxtemp_c: Double? = 0.0,

    var mintemp_c: Double? = 0.0,

    var condition: APICondition? = null

)