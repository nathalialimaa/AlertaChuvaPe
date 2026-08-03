package com.example.alertadechuvape

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alertadechuvape.ui.WelcomePage
import com.example.alertadechuvape.ui.theme.AlertaDeChuvaPeTheme

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AlertaDeChuvaPeTheme {

                WelcomePage(

                    onLogin = {

                        startActivity(
                            Intent(this, LoginActivity::class.java)
                        )

                    },

                    onRegister = {

                        startActivity(
                            Intent(this, RegisterActivity::class.java)
                        )

                    }

                )

            }

        }

    }

}