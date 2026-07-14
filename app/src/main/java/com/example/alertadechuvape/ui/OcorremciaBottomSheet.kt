package com.example.alertadechuvape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.model.Ocorrencia
import com.example.alertadechuvape.model.TiposOcorrencia
import com.example.alertadechuvape.utils.toDataHora

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcorrenciaBottomSheet(

    ocorrencia: Ocorrencia,

    onDismiss: () -> Unit,

    onVerNoMapa: () -> Unit

) {

    ModalBottomSheet(

        onDismissRequest = onDismiss,

        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)

        ) {

            Text(

                text =
                    "${TiposOcorrencia.emoji(ocorrencia.tipo)} ${ocorrencia.tipo}",

                style =
                    MaterialTheme.typography.headlineSmall

            )

            Spacer(Modifier.height(20.dp))

            Text(

                "📍 ${ocorrencia.cidade ?: "Local desconhecido"}"

            )

            Spacer(Modifier.height(8.dp))

            Text(

                "🕒 ${ocorrencia.dataHora.toDataHora()}"

            )

            Spacer(Modifier.height(20.dp))

            Text(

                "Descrição",

                style =
                    MaterialTheme.typography.titleMedium

            )

            Spacer(Modifier.height(8.dp))

            Text(

                ocorrencia.descricao
                    ?: "Sem descrição"

            )

            Spacer(Modifier.height(32.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                OutlinedButton(

                    modifier = Modifier.weight(1f),

                    onClick = onDismiss

                ) {

                    Text("Fechar")

                }

                Button(

                    modifier = Modifier.weight(1f),

                    onClick = {

                        onVerNoMapa()

                    }

                ) {

                    Text("Ver no mapa")

                }

            }

            Spacer(Modifier.height(20.dp))

        }

    }

}