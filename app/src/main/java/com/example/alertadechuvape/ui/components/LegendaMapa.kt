package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LegendaMapa() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        ItemLegenda(Color.Blue,"Alagamento")
        ItemLegenda(Color(0xFFFF9800),"Deslizamento")
        ItemLegenda(Color.Green,"Outros")

    }

}

@Composable
private fun ItemLegenda(
    cor: Color,
    texto: String
){

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){

        Box(
            Modifier
                .size(12.dp)
                .background(cor, CircleShape)
        )

        Spacer(Modifier.width(6.dp))

        Text(texto)

    }

}