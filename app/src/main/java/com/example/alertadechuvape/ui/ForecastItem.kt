package com.example.alertadechuvape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.model.Forecast
import com.example.alertadechuvape.utils.toDataCurta
import com.example.alertadechuvape.ui.components.WeatherIcon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ForecastItem(

    forecast: Forecast,

    modifier: Modifier = Modifier

) {

    Card(
        modifier = modifier
            .width(140.dp)
            .padding(end = 10.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F7FB)
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Column(

            modifier = Modifier.padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            WeatherIcon(
                url = forecast.icone,
                modifier = Modifier.size(60.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                forecast.data.toDataCurta(),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                forecast.descricao,
                color = Color.Gray
            )

            Text(
                "↑ ${forecast.temperaturaMax}°",
                color = Color.Red
            )

            Text(
                "↓ ${forecast.temperaturaMin}°",
                color = Color.Blue
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "${forecast.temperaturaMax}°",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "${forecast.temperaturaMin}°",
                    color = Color.Gray
                )

            }

        }

    }

}