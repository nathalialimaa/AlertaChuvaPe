package com.example.alertadechuvape.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun OcorrenciaMapaDialog(
    onDismiss: () -> Unit,
    onConfirm: (
        tipo: String,
        descricao: String
    ) -> Unit
){
    var cidade by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }
    var tipoOcorrencia by remember {
        mutableStateOf("")
    }



    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {

        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        "Adicionar ocorrência"
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",

                        modifier = Modifier.clickable {
                            onDismiss()
                        }
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = cidade,

                    enabled = false,

                    label = {
                        Text("Cidade")
                    },

                    onValueChange = {}
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Tipo da ocorrência")
                    },

                    value = tipoOcorrencia,

                    onValueChange = {
                        tipoOcorrencia = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = descricao,


                    label = {
                        Text("Descrição")
                    },

                    onValueChange = {
                        descricao = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Button(
                    onClick = {
                        onConfirm(
                            tipoOcorrencia,
                            descricao
                        )
                    }
                ) {
                    Text("OK")
                }



            }

        }

    }

}