package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alertadechuvape.model.Weather
import coil.compose.AsyncImage

@Composable
fun CardClima(
    weather : Weather
) {

    SectionCard {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = weather.icone,
                    contentDescription = null,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(Modifier.width(12.dp))

                Column {

                    Text(
                        "${weather.temperatura.toInt()}°C",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(weather.descricao)

                }

            }

            Spacer(Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(Modifier.height(16.dp))

            InfoClima(
                "Sensação",
                "${weather.sensacao.toInt()}°C"
            )

            InfoClima(
                "Umidade",
                "${weather.umidade}%"
            )

            InfoClima(
                "Vento",
                "${weather.vento.toInt()} km/h"
            )

        }

    }

}

@Composable
private fun InfoClima(

    titulo: String,
    valor: String

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.SpaceBetween

    ) {

        Text(titulo)

        Text(
            valor,
            fontWeight = FontWeight.Bold
        )

    }

}