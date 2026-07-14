package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.ui.ForecastItem
import com.example.alertadechuvape.viewmodel.MainViewModel

@Composable
fun CardPrevisao(

    viewModel: MainViewModel

) {

    val previsoes =

        viewModel.forecastAtual()

    Card(

        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(6.dp)

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                text = "Previsão para os próximos 10 dias",

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(

                modifier = Modifier.height(12.dp)

            )

            if (previsoes.isEmpty()) {

                Text(

                    "Carregando previsão..."

                )

            } else {

                LazyRow(

                    horizontalArrangement =

                        Arrangement.spacedBy(10.dp)

                ) {

                    items(previsoes) {

                        ForecastItem(

                            forecast = it

                        )

                    }

                }

            }

        }

    }

}