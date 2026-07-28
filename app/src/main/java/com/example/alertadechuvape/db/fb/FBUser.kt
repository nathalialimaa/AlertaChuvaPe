package com.example.alertadechuvape.db.fb

import com.example.alertadechuvape.model.User

class FBUser {

    var name: String? = null
    var email: String? = null

    var monitoramentoAtivo: Boolean = false


    fun toUser() =
        User(
            name!!,
            email!!,
            monitoramentoAtivo
        )
}

fun User.toFBUser(): FBUser {

    val fb = FBUser()

    fb.name = name
    fb.email = email
    fb.monitoramentoAtivo = monitoramentoAtivo


    return fb
}