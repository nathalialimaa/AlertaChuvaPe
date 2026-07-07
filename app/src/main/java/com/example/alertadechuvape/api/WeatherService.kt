package com.example.alertadechuvape.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import android.location.Geocoder
import java.util.Locale
class WeatherService {

    private var weatherAPI: WeatherServiceAPI

    init {

        val retrofitAPI = Retrofit.Builder()
            .baseUrl(WeatherServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherAPI = retrofitAPI.create(WeatherServiceAPI::class.java)
        Log.d(
            "WEATHER",
            WeatherServiceAPI.API_KEY
        )
    }

    fun getName(
        lat: Double,
        lng: Double,
        onResponse: (String?) -> Unit
    ) {

        search("$lat,$lng") { location ->

            Log.d(
                "WEATHER",
                """
        name = ${location?.name}
        region = ${location?.region}
        country = ${location?.country}
        lat = ${location?.lat}
        lon = ${location?.lon}
        """.trimIndent()
            )

            onResponse(
                "${location?.name}\n${location?.region}\n${location?.country}"
            )

        }

    }

    fun getLocation(
        name: String,
        onResponse: (Double?, Double?) -> Unit
    ) {

        val cidade = name
            .trim()
            .replace(Regex("\\s+"), " ")

        val consulta =
            if (cidade.contains(",")) {
                cidade
            } else {
                "$cidade, Pernambuco, Brasil"
            }

        Log.d("WEATHER", "Consulta: $consulta")

        search(consulta) { location ->
            onResponse(location?.lat, location?.lon)
        }
    }

    private fun search(
        query: String,
        onResponse: (APILocation?) -> Unit
    ) {

        val call = weatherAPI.search(query)

        call.enqueue(object : Callback<List<APILocation>?> {

            override fun onResponse(
                call: Call<List<APILocation>?>,
                response: Response<List<APILocation>?>
            ) {

                response.body()?.forEach {
                    Log.d(
                        "WEATHER",
                        "${it.name} - ${it.region} - ${it.country}"
                    )
                }
                Log.d("WEATHER", "Consulta: $query")
                val location = response.body()?.firstOrNull()

                onResponse(location)
            }

            override fun onFailure(
                call: Call<List<APILocation>?>,
                t: Throwable
            ) {

                Log.w(
                    "AlertaChuvaPE",
                    t.message ?: "Erro desconhecido"
                )

                onResponse(null)

            }

        })

    }

    private fun <T> enqueue(

        call: Call<T?>,
        onResponse: ((T?) -> Unit)? = null

    ) {

        call.enqueue(object : Callback<T?> {

            override fun onResponse(

                call: Call<T?>,
                response: Response<T?>

            ) {

                onResponse?.invoke(response.body())

            }

            override fun onFailure(

                call: Call<T?>,
                t: Throwable

            ) {

            }

        })

    }

    fun getWeather(
        lat: Double,
        lng: Double,
        onResponse: (APICurrentWeather?) -> Unit
    ) {

        val call = weatherAPI.weather("$lat,$lng")

        enqueue(call) {

            Log.d(
                "WEATHER",
                """
            name=${it?.location?.name}
            region=${it?.location?.region}
            country=${it?.location?.country}
            lat=${it?.location?.lat}
            lon=${it?.location?.lon}
            """.trimIndent()
            )

            onResponse(it)

        }

    }

    fun getEndereco(

        context: Context,
        lat: Double,
        lng: Double,
        onResponse: (String, String, String) -> Unit

    ) {

        try {

            val geocoder =
                Geocoder(context, Locale("pt", "BR"))

            val addresses =
                geocoder.getFromLocation(lat, lng, 1)

            if (!addresses.isNullOrEmpty()) {

                val endereco = addresses.first()

                val bairro =
                    endereco.subLocality
                        ?: endereco.locality
                        ?: ""

                val cidade =
                    endereco.subAdminArea
                        ?: endereco.locality
                        ?: ""

                val estado =
                    endereco.adminArea ?: ""

                onResponse(

                    bairro,
                    cidade,
                    estado

                )

            }

        } catch (e: Exception) {

            e.printStackTrace()

        }

    }

}