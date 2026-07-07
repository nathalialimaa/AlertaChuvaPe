package com.example.alertadechuvape.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardAlerta() {

    SectionCard {

        Row {

            Icon(
                Icons.Default.Warning,
                null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text("Alerta Meteorológico")

                Text("Nenhum alerta ativo")

            }

        }

    }

}