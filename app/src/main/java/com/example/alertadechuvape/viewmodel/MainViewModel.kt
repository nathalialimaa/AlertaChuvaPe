package com.example.alertadechuvape.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.alertadechuvape.model.Ocorrencia
import com.google.android.gms.maps.model.LatLng
import androidx.compose.runtime.mutableStateOf
import com.example.alertadechuvape.model.User
import com.example.alertadechuvape.db.fb.FBDatabase
import com.example.alertadechuvape.db.fb.FBOcorrencia
import com.example.alertadechuvape.db.fb.toFBOcorrencia
import com.example.alertadechuvape.db.fb.FBUser
import androidx.lifecycle.ViewModelProvider
import com.example.alertadechuvape.api.WeatherService
import com.example.alertadechuvape.api.toWeather
import com.example.alertadechuvape.model.Weather
import android.content.Context
import androidx.compose.runtime.mutableStateMapOf
import com.example.alertadechuvape.model.Forecast
import com.example.alertadechuvape.model.LocalizacaoAtual
import com.example.alertadechuvape.ui.nav.BottomNavItem

import com.example.alertadechuvape.api.toForecast
class MainViewModel(
    private val db: FBDatabase,
    private val weatherService: WeatherService

) : ViewModel(), FBDatabase.Listener {
    init {
        db.setListener(this)
    }

    private val _ocorrencias =
        mutableStateListOf<Ocorrencia>()

    val ocorrencias
        get() = _ocorrencias.toList()

    private val _user = mutableStateOf<User?>(null)

    val user: User?
        get() = _user.value
    private val _weather = mutableStateOf(Weather.LOADING)

    private val _localizacao = mutableStateOf(

        LocalizacaoAtual(

            bairro = "...",
            cidade = "...",
            estado = "..."

        )

    )

    val localizacao
        get() = _localizacao.value
    val weather : Weather
        get() = _weather.value

    private val _forecast =

        mutableStateMapOf<String, List<Forecast>>()

    private val _page =
        mutableStateOf(BottomNavItem.HomeButton.route)

    var page: String
        get() = _page.value
        set(value) {
            _page.value = value
        }
    val quantidadeAlagamentos

        get() =

            ocorrencias.count {

                it.tipo == "Alagamento"

            }

    val quantidadeDeslizamentos

        get() =

            ocorrencias.count {

                it.tipo == "Deslizamento"

            }

    private val _pagina = mutableStateOf("home")

    var pagina: String
        get() = _pagina.value
        set(value) {
            _pagina.value = value
        }

    private val _ocorrenciaSelecionada = mutableStateOf<Ocorrencia?>(null)

    var ocorrenciaSelecionada: Ocorrencia?
        get() = _ocorrenciaSelecionada.value
        set(value) {
            _ocorrenciaSelecionada.value = value
        }
    fun remove(
        ocorrencia: Ocorrencia
    ) {

        db.remove(
            ocorrencia.toFBOcorrencia()
        )

    }
    fun buscarNomeCidade(
        local: LatLng,
        onResult: (String) -> Unit
    ) {
        weatherService.getName(
            local.latitude,
            local.longitude
        ) { nome ->

            onResult(nome ?: "")

        }
    }

    fun addOcorrencia(
        tipo: String,
        cidade: String,
        descricao: String,
        local: LatLng
    ) {

        weatherService.getLocation(cidade) { lat, lng ->

            if (lat != null && lng != null) {

                db.add(

                    Ocorrencia(
                        tipo = tipo,
                        cidade = cidade,
                        descricao = descricao,
                        local = LatLng(lat, lng)
                    ).toFBOcorrencia()

                )

            }

        }

    }

    fun addOcorrencia(
        tipo: String,
        cidade: String,
        descricao: String
    ) {

        weatherService.getLocation(cidade) { lat, lng ->

            if (lat != null && lng != null) {

                db.add(

                    Ocorrencia(
                        tipo = tipo,
                        cidade = cidade,
                        descricao = descricao,
                        local = LatLng(lat, lng)
                    ).toFBOcorrencia()

                )

            }

        }

    }

    fun add(
        tipo: String,
        local: LatLng? = null
    ) {

        if (local != null) {

            weatherService.getName(
                local.latitude,
                local.longitude
            ) { nome ->

                db.add(
                    Ocorrencia(
                        tipo = tipo,
                        descricao = nome,
                        local = local
                    ).toFBOcorrencia()
                )

            }

        } else {

            db.add(
                Ocorrencia(
                    tipo = tipo
                ).toFBOcorrencia()
            )

        }

    }

    override fun onUserLoaded(user: FBUser) {
        _user.value = user.toUser()
    }

    override fun onUserSignOut() {

        _user.value = null

        _ocorrencias.clear()

    }

    override fun onOcorrenciaAdded(
        ocorrencia: FBOcorrencia
    ) {

        _ocorrencias.add(
            ocorrencia.toOcorrencia()
        )

    }

    override fun onOcorrenciaRemoved(
        ocorrencia: FBOcorrencia
    ) {

        _ocorrencias.remove(
            ocorrencia.toOcorrencia()
        )

    }

    override fun onOcorrenciaUpdated(
        ocorrencia: FBOcorrencia
    ) {

        val ocorrenciaAtualizada =
            ocorrencia.toOcorrencia()

        val indice =
            _ocorrencias.indexOfFirst {

                it.id == ocorrenciaAtualizada.id

            }

        if (indice != -1) {

            _ocorrencias[indice] =
                ocorrenciaAtualizada

        }

    }
    fun carregarClimaAtual(

        local: LatLng

    ) {

        weatherService.getWeather(

            local.latitude,
            local.longitude

        ) {

            if (it != null)

                _weather.value = it.toWeather()

        }

    }

    fun carregarCidadeAtual(

        context: Context,
        local: LatLng

    ) {

        weatherService.getEndereco(

            context,
            local.latitude,
            local.longitude

        ) { bairro, cidade, estado ->

            _localizacao.value =

                LocalizacaoAtual(

                    bairro,
                    cidade,
                    estado

                )

        }

        carregarClimaAtual(local)

    }

    fun forecast(

        cidade: String

    ): List<Forecast> {

        return _forecast.getOrPut(cidade) {

            loadForecast(cidade)

            emptyList()

        }

    }

    fun forecastAtual(): List<Forecast> {

        val cidade =

            localizacao.cidade

        if (cidade.isBlank() || cidade == "...")

            return emptyList()

        return forecast(cidade)

    }


    private fun loadForecast(

        cidade: String

    ) {

        weatherService.getForecast(cidade) {

            it?.let {

                _forecast[cidade] =

                    it.toForecast()

            }

        }

    }
}

class MainViewModelFactory(
    private val db: FBDatabase,
    private val weatherService: WeatherService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                db,
                weatherService
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}