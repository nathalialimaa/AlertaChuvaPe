package com.example.alertadechuvape.db.fb

import com.example.alertadechuvape.model.Ocorrencia
import com.google.android.gms.maps.model.LatLng

class FBOcorrencia {
    var id: String? = null
    var tipo: String? = null
    var descricao: String? = null

    var lat: Double? = null
    var lng: Double? = null

    fun toOcorrencia(): Ocorrencia {

        val location =
            if (lat != null && lng != null)
                LatLng(lat!!, lng!!)
            else
                null

        return Ocorrencia(
            id = id ?: "",
            tipo = tipo!!,
            descricao = descricao,
            local = location
        )
    }
}

fun Ocorrencia.toFBOcorrencia(): FBOcorrencia {

    val fb = FBOcorrencia()

    fb.id = id
    fb.tipo = tipo
    fb.descricao = descricao

    fb.lat = local?.latitude
    fb.lng = local?.longitude

    return fb
}