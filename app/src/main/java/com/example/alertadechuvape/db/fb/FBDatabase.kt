package com.example.alertadechuvape.db.fb

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
                refUser.collection("ocorrencias")
                    .addSnapshotListener { snapshots, ex ->

                        if (ex != null) return@addSnapshotListener

                        snapshots?.documentChanges?.forEach { change ->

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

        if (auth.currentUser == null)
            throw RuntimeException("Usuário não autenticado.")

        if (ocorrencia.tipo.isNullOrBlank())
            throw RuntimeException("Ocorrência inválida.")

        val uid = auth.currentUser!!.uid

            val documento =
                db.collection("users")
                    .document(uid)
                    .collection("ocorrencias")
                    .document()

            ocorrencia.id = documento.id

            documento.set(ocorrencia)


    }

    fun remove(ocorrencia: FBOcorrencia) {

        if (auth.currentUser == null)
            throw RuntimeException("Usuário não autenticado.")

        if (ocorrencia.tipo.isNullOrBlank())
            throw RuntimeException("Ocorrência inválida.")

        val uid = auth.currentUser!!.uid

        db.collection("users")
            .document(uid)
            .collection("ocorrencias")
            .document(ocorrencia.id!!)
            .delete()

    }
}