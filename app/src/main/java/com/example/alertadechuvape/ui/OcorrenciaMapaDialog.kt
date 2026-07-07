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
import androidx.compose.material3.*
import com.example.alertadechuvape.model.TiposOcorrencia
import androidx.compose.material3.ExposedDropdownMenuAnchorType

@OptIn(ExperimentalMaterial3Api::class)
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
    var expanded by remember {
        mutableStateOf(false)
    }

    val formularioValido =

        tipoOcorrencia.isNotBlank() &&
                descricao.isNotBlank()

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

                    value = descricao,

                    label = {
                        Text("Descrição")
                    },

                    placeholder = {
                        Text("Ex.: água cobrindo metade da rua")
                    },

                    onValueChange = {

                        if (it.length <= 200) {

                            descricao = it

                        }

                    }

                )
                Text(

                    "${descricao.length}/200",

                    style = MaterialTheme.typography.bodySmall

                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Button(

                    modifier = Modifier.fillMaxWidth(),

                    enabled = formularioValido,

                    onClick = {

                        onConfirm(
                            tipoOcorrencia,
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