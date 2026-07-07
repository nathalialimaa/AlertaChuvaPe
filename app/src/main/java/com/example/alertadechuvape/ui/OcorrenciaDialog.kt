package com.example.alertadechuvape.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import com.example.alertadechuvape.model.TiposOcorrencia
import androidx.compose.material3.ExposedDropdownMenuAnchorType
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcorrenciaDialog(
    onDismiss: () -> Unit,
    onConfirm: (
        tipo: String,
        cidade: String,
        descricao: String
    ) -> Unit
) {
    var cidade by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }
    var tipoOcorrencia by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
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

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),

                    value = cidade,


                    label = {
                        Text("Cidade")
                    },

                    onValueChange = {
                        cidade = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                ExposedDropdownMenuBox(

                    expanded = expanded,

                    onExpandedChange = {

                        expanded = !expanded

                    }

                ) {

                    OutlinedTextField(

                        modifier = Modifier
                            .menuAnchor(
                                ExposedDropdownMenuAnchorType.PrimaryNotEditable
                            )
                            .fillMaxWidth(),

                        readOnly = true,

                        label = {
                            Text("Tipo da ocorrência")
                        },

                        value =
                            if (tipoOcorrencia.isBlank()) {

                                ""

                            } else {

                                "${TiposOcorrencia.emoji(tipoOcorrencia)} $tipoOcorrencia"

                            },

                        onValueChange = {},

                        trailingIcon = {

                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)

                        }

                    )

                    ExposedDropdownMenu(

                        expanded = expanded,

                        onDismissRequest = {

                            expanded = false

                        }

                    ) {

                        TiposOcorrencia.lista.forEach { opcao ->

                            DropdownMenuItem(

                                text = {

                                    Text(
                                        "${TiposOcorrencia.emoji(opcao)} $opcao"
                                    )

                                },

                                onClick = {

                                    tipoOcorrencia = opcao
                                    expanded = false

                                }

                            )

                        }

                    }

                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Descrição")
                    },

                    value = descricao,

                    onValueChange = {
                        descricao = it
                    }

                )
                val formularioValido =

                    cidade.isNotBlank() &&
                            tipoOcorrencia.isNotBlank() &&
                            descricao.isNotBlank()
                Button(

                    enabled = formularioValido,

                    onClick = {

                        onConfirm(
                            tipoOcorrencia,
                            cidade,
                            descricao
                        )

                    }

                ) {

                    Text("Cadastrar")

                }

            }

        }

    }

}