package com.example.alertadechuvape.ui

import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alertadechuvape.MainActivity
import com.example.alertadechuvape.RegisterActivity
import androidx.activity.compose.LocalActivity

@Composable
fun LoginPage(
    modifier: Modifier = Modifier
) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    val activity = LocalActivity.current!!

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "AlertaChuvaPE",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            label = {
                Text("E-mail")
            },
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            label = {
                Text("Senha")
            },
            onValueChange = {
                password = it
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity) { task ->

                        if (task.isSuccessful) {


                            Toast.makeText(
                                activity,
                                "Login OK!",
                                Toast.LENGTH_LONG
                            ).show()

                        } else {

                            Toast.makeText(
                                activity,
                                "Login FALHOU!",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }

            },
            enabled =
                email.isNotBlank() &&
                        password.isNotBlank()
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                activity.startActivity(
                    Intent(
                        activity,
                        RegisterActivity::class.java
                    )
                )

            }
        ) {
            Text("Registrar-se")
        }

    }
}