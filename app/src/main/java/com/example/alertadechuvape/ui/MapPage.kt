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
import com.example.alertadechuvape.model.TipoOcorrenciaMapa
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.maps.CameraUpdateFactory
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.google.android.gms.location.LocationServices
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.alertadechuvape.ui.components.LegendaMapa
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.ui.graphics.Color


@SuppressLint("MissingPermission")
private fun centralizarNoUsuario(
    context: android.content.Context,
    cameraPositionState: com.google.maps.android.compose.CameraPositionState
) {

    val client =
        LocationServices.getFusedLocationProviderClient(context)

    client.lastLocation.addOnSuccessListener { location ->

        val destino =

            if (location != null) {

                LatLng(
                    location.latitude,
                    location.longitude
                )

            } else {

                LatLng(
                    -8.0476,
                    -34.8770
                ) // Recife

            }

        cameraPositionState.move(

            CameraUpdateFactory.newLatLngZoom(

                destino,

                13f

            )

        )

    }

}
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

    LaunchedEffect(hasLocationPermission) {

        if (hasLocationPermission) {

            centralizarNoUsuario(

                context,

                cameraPositionState

            )

        } else {

            cameraPositionState.move(

                CameraUpdateFactory.newLatLngZoom(

                    LatLng(-8.0476, -34.8770),

                    13f

                )

            )

        }

    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            Text(
                text = "Mapa de Ocorrências",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Text(
                text = "Visualize ocorrências próximas ou toque no mapa para registrar uma ocorrência.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
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

                        ocorrencia.local?.let { local ->

                            Marker(
                                state = rememberUpdatedMarkerState(local),
                                title = ocorrencia.tipo,
                                snippet = ocorrencia.descricao,
                                icon = BitmapDescriptorFactory.defaultMarker(
                                    TipoOcorrenciaMapa.cor(ocorrencia.tipo)
                                )
                            )

                        }

                    }


                }
                Spacer(Modifier.height(16.dp))

                LegendaMapa()

            }
        }

        FloatingActionButton(

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),

            onClick = {

                centralizarNoUsuario(
                    context,
                    cameraPositionState
                )

            }

        ) {

            Icon(
                Icons.Default.MyLocation,
                null
            )

        }
    }
}