package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.model.Ocorrencia
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun CardMapa(
    ocorrencias: List<Ocorrencia>
) {

    val centro = remember(ocorrencias) {
        ocorrencias.firstOrNull()?.local
            ?: LatLng(-8.0476, -34.8770)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            centro,
            11f
        )
    }



        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                scrollGesturesEnabled = false,
                zoomGesturesEnabled = false,
                rotationGesturesEnabled = false,
                tiltGesturesEnabled = false
            )
        ) {

            ocorrencias.forEach { ocorrencia ->

                ocorrencia.local?.let { local ->

                    Marker(
                        state = rememberMarkerState(position = local),
                        title = ocorrencia.tipo,
                        snippet = ocorrencia.descricao
                    )

                }

            }

        }


}