package com.example.alertadechuvape.db.fb

import com.example.alertadechuvape.model.User

class FBUser {

    var name: String? = null
    var email: String? = null

    fun toUser() =
        User(
            name!!,
            email!!
        )
}

fun User.toFBUser(): FBUser {

    val fb = FBUser()

    fb.name = name
    fb.email = email

    return fb
}