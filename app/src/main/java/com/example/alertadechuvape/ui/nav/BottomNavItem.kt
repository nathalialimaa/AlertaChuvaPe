package com.example.alertadechuvape.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object HomeButton :
        BottomNavItem(
            "Início",
            Icons.Default.Home,
            "home"
        )

    object OcorrenciasButton :
        BottomNavItem(
            "Ocorrências",
            Icons.Default.Warning,
            "ocorrencias"
        )

    object MapButton :
        BottomNavItem(
            "Mapa",
            Icons.Default.LocationOn,
            "mapa"
        )
}