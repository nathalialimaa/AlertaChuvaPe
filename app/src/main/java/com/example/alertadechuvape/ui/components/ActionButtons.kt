package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionButtons() {

    Column {

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {

            Text("Ver mapa")

        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {

            Text("Reportar ocorrência")

        }

    }

}