package com.example.alertadechuvape.db.fb

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore

class FBDatabase {

    interface Listener {

        fun onUserLoaded(user: FBUser)

        fun onUserSignOut()

        fun onOcorrenciaAdded(ocorrencia: FBOcorrencia)

        fun onOcorrenciaUpdated(ocorrencia: FBOcorrencia)

        fun onOcorrenciaRemoved(ocorrencia: FBOcorrencia)

    }

    private val auth = Firebase.auth

    private val db = Firebase.firestore

    private var ocorrenciasReg: ListenerRegistration? = null

    private var listener: Listener? = null

    fun setListener(listener: Listener? = null) {
        this.listener = listener
    }
    init {

        auth.addAuthStateListener { auth ->

            if (auth.currentUser == null) {

                ocorrenciasReg?.remove()

                listener?.onUserSignOut()

                return@addAuthStateListener
            }

            val refUser =
                db.collection("users")
                    .document(auth.currentUser!!.uid)

            refUser.get().addOnSuccessListener {

                it.toObject(FBUser::class.java)?.let { usuario ->

                    listener?.onUserLoaded(usuario)

                }

            }

            ocorrenciasReg =
                db.collection("ocorrencias")
                    .addSnapshotListener { snapshots, ex ->

                        if (ex != null) return@addSnapshotListener

                        snapshots?.documentChanges?.forEach { change ->

                            Log.d("DOC_ID", change.document.id)
                            Log.d("DOC_DATA", change.document.data.toString())

                            if (change.document.data.isEmpty()) {
                                Log.e("FIREBASE", "Documento vazio! Ignorando...")
                                return@forEach
                            }

                            val fbOcorrencia =
                                change.document.toObject(FBOcorrencia::class.java)

                            fbOcorrencia.id = change.document.id

                            when (change.type) {

                                com.google.firebase.firestore.DocumentChange.Type.ADDED ->
                                    listener?.onOcorrenciaAdded(fbOcorrencia)

                                com.google.firebase.firestore.DocumentChange.Type.MODIFIED ->
                                    listener?.onOcorrenciaUpdated(fbOcorrencia)

                                com.google.firebase.firestore.DocumentChange.Type.REMOVED ->
                                    listener?.onOcorrenciaRemoved(fbOcorrencia)
                            }

                        }

                    }

        }

    }

    fun register(user: FBUser) {

        if (auth.currentUser == null)
            throw RuntimeException("Usuário não autenticado.")

        val uid = auth.currentUser!!.uid

        db.collection("users")
            .document(uid)
            .set(user)
    }

    fun add(ocorrencia: FBOcorrencia) {
        Log.d("TESTE", "FBDatabase.add() chamado")

        if (auth.currentUser == null)
            throw RuntimeException("Usuário não autenticado.")

        if (ocorrencia.tipo.isNullOrBlank())
            throw RuntimeException("Ocorrência inválida.")

        val uid = auth.currentUser!!.uid

        ocorrencia.uid = uid

        val documento =
            db.collection("ocorrencias")
                .document()

            ocorrencia.id = documento.id
        Log.d("ANTES_SET", ocorrencia.tipo.toString())
        Log.d("ANTES_SET", ocorrencia.cidade.toString())
        Log.d("ANTES_SET", ocorrencia.descricao.toString())
        Log.d("ANTES_SET", ocorrencia.lat.toString())
        Log.d("ANTES_SET", ocorrencia.lng.toString())
        Log.d("ANTES_SET", ocorrencia.uid.toString())
        documento
            .set(ocorrencia)
            .addOnSuccessListener {
                Log.d("TESTE", "Salvou no Firestore")
            }
            .addOnFailureListener {
                Log.e("TESTE", "Erro ao salvar", it)
            }

        Log.d("TESTE", "====== OCORRENCIA ======")
        Log.d("TESTE", "id=${ocorrencia.id}")
        Log.d("TESTE", "tipo=${ocorrencia.tipo}")
        Log.d("TESTE", "cidade=${ocorrencia.cidade}")
        Log.d("TESTE", "descricao=${ocorrencia.descricao}")
        Log.d("TESTE", "lat=${ocorrencia.lat}")
        Log.d("TESTE", "lng=${ocorrencia.lng}")
        Log.d("TESTE", "uid=${ocorrencia.uid}")


    }

    fun remove(ocorrencia: FBOcorrencia) {

        if (auth.currentUser == null)
            throw RuntimeException("Usuário não autenticado.")

        if (ocorrencia.tipo.isNullOrBlank())
            throw RuntimeException("Ocorrência inválida.")

        val uid = auth.currentUser!!.uid

        db.collection("ocorrencias")
            .document(ocorrencia.id!!)
            .delete()

    }

    fun updateMonitoramento(

        ativo: Boolean

    ) {

        val uid = auth.currentUser?.uid ?: return

        db.collection("users")

            .document(uid)

            .update(

                "monitoramentoAtivo",

                ativo

            )

    }
}