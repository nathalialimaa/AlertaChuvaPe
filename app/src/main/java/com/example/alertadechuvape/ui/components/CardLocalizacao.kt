package com.example.alertadechuvape.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alertadechuvape.model.Weather
import com.example.alertadechuvape.model.LocalizacaoAtual
@Composable
fun CardLocalizacao(
    localizacao: LocalizacaoAtual
) {

    SectionCard {

        Row {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    "Sua localização",
                    fontSize = 18.sp
                )

                Text(localizacao.bairro)

                Text(

                    "${localizacao.cidade} - ${localizacao.estado}",
                            fontSize = 14.sp

                )

            }

        }

    }

}