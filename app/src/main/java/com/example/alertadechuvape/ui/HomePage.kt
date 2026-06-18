package com.example.alertadechuvape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.alertadechuvape.viewmodel.MainViewModel

@Composable
fun HomePage(
    viewModel: MainViewModel
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text("AlertaChuvaPE")

        Text(
            "Monitoramento colaborativo de chuvas e alagamentos"
        )

    }
}