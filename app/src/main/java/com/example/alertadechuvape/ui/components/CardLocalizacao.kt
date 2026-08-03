package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alertadechuvape.model.LocalizacaoAtual
import androidx.compose.ui.Alignment
import com.example.alertadechuvape.model.Ocorrencia

@Composable
fun CardLocalizacao(
    localizacao: LocalizacaoAtual,
    ocorrencias: List<Ocorrencia>
) {

    SectionCard {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF0D2B45)
            )

            Spacer(Modifier.width(8.dp))

            Column {

                Text(
                    "Sua localização",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(localizacao.bairro)

                Text(
                    "${localizacao.cidade} - ${localizacao.estado}"
                )

            }

        }

        Spacer(Modifier.height(12.dp))

        CardMapa(ocorrencias)

    }

}