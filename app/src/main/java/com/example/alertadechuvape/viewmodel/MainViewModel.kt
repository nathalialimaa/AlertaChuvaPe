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


class MainViewModel(
    private val db: FBDatabase
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

    fun remove(
        ocorrencia: Ocorrencia
    ) {

        db.remove(
            ocorrencia.toFBOcorrencia()
        )

    }

    fun add(
        tipo: String,
        local: LatLng? = null
    ) {

        db.add(
            Ocorrencia(
                tipo = tipo,
                local = local
            ).toFBOcorrencia()
        )

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
}

class MainViewModelFactory(
    private val db: FBDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}