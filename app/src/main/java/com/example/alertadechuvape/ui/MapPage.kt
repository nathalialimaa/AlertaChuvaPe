package com.example.alertadechuvape.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.alertadechuvape.viewmodel.MainViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import androidx.compose.runtime.remember
import com.google.maps.android.compose.rememberUpdatedMarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
@Composable
fun MapPage(
    viewModel: MainViewModel,
    onMapClick: (LatLng) -> Unit
) {
    val cameraPositionState =
        rememberCameraPositionState()
    val context = LocalContext.current

    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val recife = remember {
        MarkerState(
            position = LatLng(-8.05, -34.9)
        )
    }

    val caruaru = remember {
        MarkerState(
            position = LatLng(-8.27, -35.98)
        )
    }

    val joaoPessoa = remember {
        MarkerState(
            position = LatLng(-7.12, -34.84)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState =
            cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = hasLocationPermission
        ),

        uiSettings = MapUiSettings(
            myLocationButtonEnabled = true
        ),
        onMapClick = { latLng ->
            onMapClick(latLng)
        }
    ) {
        viewModel.ocorrencias.forEach { ocorrencia ->

            if (ocorrencia.local != null) {

                Marker(
                    state = rememberUpdatedMarkerState(
                        position = ocorrencia.local
                    ),
                    title = ocorrencia.tipo,
                    snippet = ocorrencia.local.toString()
                )

            }

        }
        Marker(
            state = recife,
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_BLUE
            )
        )

        Marker(
            state = caruaru,
            title = "Caruaru",
            snippet = "Marcador em Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_GREEN
            )
        )

        Marker(
            state = joaoPessoa,
            title = "João Pessoa",
            snippet = "Marcador em João Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_RED
            )
        )

    }

}