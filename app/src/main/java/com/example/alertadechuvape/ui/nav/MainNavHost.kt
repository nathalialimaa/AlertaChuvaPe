package com.example.alertadechuvape.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alertadechuvape.ui.HomePage
import com.example.alertadechuvape.ui.MapPage
import com.example.alertadechuvape.ui.OcorrenciasPage
import com.example.alertadechuvape.viewmodel.MainViewModel
import com.google.android.gms.maps.model.LatLng
@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    onMapClick: (LatLng) -> Unit,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.HomeButton.route,
        modifier = modifier
    ) {

        composable(
            route = BottomNavItem.HomeButton.route
        ) {
            HomePage(
                viewModel = viewModel
            )
        }

        composable(
            route = BottomNavItem.OcorrenciasButton.route
        ) {
            OcorrenciasPage(
                viewModel = viewModel
            )
        }

        composable(
            route = BottomNavItem.MapButton.route
        ) {
            MapPage(
                viewModel = viewModel,
                onMapClick = onMapClick
            )
        }
    }
}