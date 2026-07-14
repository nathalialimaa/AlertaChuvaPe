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
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun ForecastItem(

    forecast: Forecast,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier
            .width(160.dp)
            .padding(end = 10.dp),

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(5.dp)

    ) {

        Column(

            modifier = Modifier.padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AsyncImage(

                model = forecast.icone,

                contentDescription = forecast.descricao,

                modifier = Modifier.size(56.dp),

                contentScale = ContentScale.Fit

            )

            Spacer(Modifier.height(8.dp))

            Text(

                forecast.data.toDataCurta(),

                style = MaterialTheme.typography.titleSmall

            )

            Spacer(Modifier.height(6.dp))

            Text(

                forecast.descricao,

                style = MaterialTheme.typography.bodyMedium

            )

            Spacer(Modifier.height(10.dp))

            Text(

                "↑ ${forecast.temperaturaMax}°"

            )

            Text(

                "↓ ${forecast.temperaturaMin}°"

            )

        }

    }

}