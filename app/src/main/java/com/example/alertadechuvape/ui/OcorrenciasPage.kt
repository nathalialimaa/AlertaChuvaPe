package com.example.alertadechuvape.ui

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alertadechuvape.model.Ocorrencia
import com.example.alertadechuvape.viewmodel.MainViewModel

@Composable
fun OcorrenciasPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val lista =
        viewModel.ocorrencias

    val activity =
        LocalActivity.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        items(
            lista,
            key = { ocorrencia ->
                "${ocorrencia.tipo}_${ocorrencia.local}"
            }
        ) { ocorrencia ->

            OcorrenciaItem(

                ocorrencia = ocorrencia,

                onClick = {

                    Toast.makeText(
                        activity,
                        ocorrencia.tipo,
                        Toast.LENGTH_SHORT
                    ).show()
                },

                onClose = {

                    viewModel.remove(
                        ocorrencia
                    )
                }
            )
        }
    }
}

@Composable
fun OcorrenciaItem(
    ocorrencia: Ocorrencia,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.Warning,
            contentDescription = ""
        )

        Spacer(
            modifier = Modifier.size(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = ocorrencia.tipo,
                fontSize = 22.sp
            )

            Text(
                text =
                    ocorrencia.descricao
                        ?: "Sem descrição",
                fontSize = 16.sp
            )
        }

        IconButton(
            onClick = onClose
        ) {

            Icon(
                Icons.Default.Close,
                contentDescription = "Excluir"
            )
        }
    }
}