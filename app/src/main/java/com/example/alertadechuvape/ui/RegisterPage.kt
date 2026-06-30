package com.example.alertadechuvape.ui

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.activity.compose.LocalActivity

@Composable
fun RegisterPage(
    modifier: Modifier = Modifier
) {

    var nome by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var senha by rememberSaveable {
        mutableStateOf("")
    }

    var confirmarSenha by rememberSaveable {
        mutableStateOf("")
    }

    val activity = LocalActivity.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Cadastro")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            label = {
                Text("Nome")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("E-mail")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {
                senha = it
            },
            label = {
                Text("Senha")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmarSenha,
            onValueChange = {
                confirmarSenha = it
            },
            label = {
                Text("Confirmar senha")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                Firebase.auth
                    .createUserWithEmailAndPassword(
                        email,
                        senha
                    )
                    .addOnCompleteListener(activity!!) { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(
                                activity,
                                "Registro OK!",
                                Toast.LENGTH_LONG
                            ).show()


                        } else {
                            Toast.makeText(
                                activity,
                                "Falhou: ${task.exception?.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    }

            },
            enabled =
                nome.isNotBlank() &&
                        email.isNotBlank() &&
                        senha.isNotBlank() &&
                        senha == confirmarSenha

        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                nome = ""
                email = ""
                senha = ""
                confirmarSenha = ""

            }
        ) {
            Text("Limpar")
        }

    }
}