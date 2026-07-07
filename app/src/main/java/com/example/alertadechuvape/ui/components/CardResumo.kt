package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alertadechuvape.viewmodel.MainViewModel

@Composable
fun CardResumo(
    viewModel: MainViewModel
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ResumoItem(

            "Ocorrências",

            viewModel.ocorrencias.size.toString()

        )

        ResumoItem(

            "Alagamentos",

            viewModel.quantidadeAlagamentos.toString()

        )

        ResumoItem(

            "Deslizamentos",

            viewModel.quantidadeDeslizamentos.toString()

        )

    }

}

@Composable
private fun RowScope.ResumoItem(

    titulo: String,
    valor: String

) {

    Card(
        modifier = Modifier.weight(1f)
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(valor)

            Spacer(modifier = Modifier.height(6.dp))

            Text(titulo)

        }

    }

}