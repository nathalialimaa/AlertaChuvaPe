package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alertadechuvape.viewmodel.MainViewModel
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Flood
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.sp

@Composable
fun CardResumo(
    viewModel: MainViewModel
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ResumoItem(
            "Ocorrências",
            viewModel.ocorrencias.size.toString(),
            Color(0xFF1565C0),
            Icons.Default.Warning
        )

        ResumoItem(
            "Alagamentos",
            viewModel.quantidadeAlagamentos.toString(),
            Color(0xFFD32F2F),
            Icons.Default.Flood
        )

        ResumoItem(
            "Deslizamentos",
            viewModel.quantidadeDeslizamentos.toString(),
            Color(0xFFF9A825),
            Icons.Default.Landscape
        )

    }

}
@Composable
private fun RowScope.ResumoItem(
    titulo: String,
    valor: String,
    cor: Color,
    icone: ImageVector
) {

    Card(
        modifier = Modifier.weight(1f),
        colors = CardDefaults.cardColors(
            containerColor = cor
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icone,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = valor,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = titulo,
                color = Color.White
            )

        }

    }

}