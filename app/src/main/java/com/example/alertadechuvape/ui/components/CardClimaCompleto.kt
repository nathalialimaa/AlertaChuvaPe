package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.viewmodel.MainViewModel

@Composable
fun CardClimaCompleto(
    viewModel: MainViewModel
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF012C9B)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            Modifier.padding(16.dp)
        ) {

            ClimaContent(viewModel)

            HorizontalDivider(
                color = Color.White.copy(alpha = .3f)
            )

            Spacer(Modifier.height(16.dp))

            PrevisaoContent(viewModel)

        }

    }

}