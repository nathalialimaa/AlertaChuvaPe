package com.example.alertadechuvape.db.fb

import com.example.alertadechuvape.model.Ocorrencia
import com.google.android.gms.maps.model.LatLng

class FBOcorrencia {
    var id: String? = null
    var tipo: String? = null
    var descricao: String? = null

    var cidade: String? = null

    var lat: Double? = null
    var lng: Double? = null

    var dataHora: Long = 0L

    var uid: String? = null


    fun toOcorrencia(): Ocorrencia {

        val location =
            if (lat != null && lng != null)
                LatLng(lat!!, lng!!)
            else
                null

        return Ocorrencia(
            id = id ?: "",
            tipo = tipo!!,
            cidade = cidade,
            descricao = descricao,
            local = location,
            dataHora = dataHora,
            uid = uid
        )
    }
}

fun Ocorrencia.toFBOcorrencia(): FBOcorrencia {

    val fb = FBOcorrencia()

    fb.id = id
    fb.tipo = tipo
    fb.descricao = descricao
    fb.cidade = cidade
    fb.lat = local?.latitude
    fb.lng = local?.longitude
    fb.dataHora = dataHora
    fb.uid = uid

    return fb
}