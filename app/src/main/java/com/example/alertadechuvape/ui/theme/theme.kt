package com.example.alertadechuvape.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun AlertaDeChuvaPeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}