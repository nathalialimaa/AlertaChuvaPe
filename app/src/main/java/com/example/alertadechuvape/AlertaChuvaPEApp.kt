package com.example.alertadechuvape

import android.app.Application
import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AlertaChuvaPEApp : Application() {

    private val FLAGS =
        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK

    override fun onCreate() {
        super.onCreate()

        Firebase.auth.addAuthStateListener { firebaseAuth ->

            if (firebaseAuth.currentUser != null) {
                goToMain()
            } else {
                goToWelcome()
            }
        }
    }

    private fun goToMain() {
        startActivity(
            Intent(this, MainActivity::class.java)
                .setFlags(FLAGS)
        )
    }

    private fun goToWelcome() {
        startActivity(
            Intent(this, WelcomeActivity::class.java)
                .setFlags(FLAGS)
        )
    }
}