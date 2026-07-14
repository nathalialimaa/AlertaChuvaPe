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
import com.example.alertadechuvape.utils.toDataHora
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.alertadechuvape.model.TipoOcorrenciaUI
import com.example.alertadechuvape.model.TiposOcorrencia
import com.example.alertadechuvape.utils.toDataHora
import com.example.alertadechuvape.ui.ConfirmDeleteDialog
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
@Composable
fun OcorrenciasPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val lista =

        viewModel.ocorrencias

            .sortedByDescending {

                it.dataHora

            }

    val activity =
        LocalActivity.current

    var ocorrenciaSelecionada by remember {
        mutableStateOf<Ocorrencia?>(null)
    }

    ocorrenciaSelecionada?.let { ocorrencia ->

        ConfirmDeleteDialog(

            onDismiss = {

                ocorrenciaSelecionada = null

            },

            onConfirm = {

                viewModel.remove(ocorrencia)

                ocorrenciaSelecionada = null

            }

        )

    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        items(
            lista,
            key = { ocorrencia ->
                ocorrencia.id
            }
        ) { ocorrencia ->

            OcorrenciaItem(

                ocorrencia = ocorrencia,

                onClick = {

                    ocorrenciaSelecionada = ocorrencia

                },

                onClose = {

                    ocorrenciaSelecionada = ocorrencia

                }
            )
        }
    }

    ocorrenciaSelecionada?.let {

        OcorrenciaBottomSheet(

            ocorrencia = it,

            onDismiss = {

                ocorrenciaSelecionada = null

            },

            onVerNoMapa = {

                viewModel.ocorrenciaSelecionada = it

                viewModel.pagina = "mapa"

                ocorrenciaSelecionada = null

            }

        )

    }
}

@Composable
fun OcorrenciaItem(

    ocorrencia: Ocorrencia,

    onClick: () -> Unit,

    onClose: () -> Unit,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )

    ) {

        Row {

            Box(

                modifier = Modifier
                    .width(8.dp)
                    .height(130.dp)
                    .background(
                        TipoOcorrenciaUI.cor(
                            ocorrencia.tipo
                        )
                    )

            )

            Column(

                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)

            ) {

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {

                    Text(

                        text =
                            "${TiposOcorrencia.emoji(ocorrencia.tipo)} ${ocorrencia.tipo}",

                        style =
                            MaterialTheme.typography.titleMedium

                    )

                    IconButton(

                        onClick = onClose

                    ) {

                        Icon(

                            imageVector = Icons.Default.Close,

                            contentDescription = "Excluir"

                        )

                    }

                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(

                    ocorrencia.descricao
                        ?: "Sem descrição"

                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(

                    "📍 ${ocorrencia.cidade ?: "Local desconhecido"}",

                    style =
                        MaterialTheme.typography.bodySmall

                )

                Text(

                    "🕒 ${ocorrencia.dataHora.toDataHora()}",

                    style =
                        MaterialTheme.typography.bodySmall

                )

            }

        }

    }

}