package com.example.alertadechuvape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.alertadechuvape.ui.RegisterPage
import com.example.alertadechuvape.ui.theme.AlertaDeChuvaPeTheme

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AlertaDeChuvaPeTheme {

                RegisterPage(
                    modifier = Modifier.fillMaxSize()
                )

            }

        }
    }
}