package com.example.alertadechuvape.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.ui.components.ActionButtons
import com.example.alertadechuvape.ui.components.CardAlerta
import com.example.alertadechuvape.ui.components.CardLocalizacao
import com.example.alertadechuvape.ui.components.CardMapa
import com.example.alertadechuvape.ui.components.CardResumo
import com.example.alertadechuvape.viewmodel.MainViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.NotificationsOff
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.alertadechuvape.ui.nav.BottomNavItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.example.alertadechuvape.R
import com.example.alertadechuvape.ui.components.CardClimaCompleto

@Composable
fun HomePage(
    viewModel: MainViewModel
) {
    val weather = viewModel.weather


    Box(
        modifier = Modifier.fillMaxSize()
    ) {



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.92f)
                    )
                ) {


                    Spacer(modifier = Modifier.height(16.dp))

                    CardLocalizacao(
                        localizacao = viewModel.localizacao,
                        ocorrencias = viewModel.ocorrencias
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CardClimaCompleto(viewModel)

                    Spacer(modifier = Modifier.height(16.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    CardAlerta(viewModel)

                    Spacer(modifier = Modifier.height(16.dp))

                    CardResumo(viewModel)

                    Spacer(modifier = Modifier.height(24.dp))

                    ActionButtons(

                        onMapa = {
                            viewModel.page = BottomNavItem.MapButton.route
                        },

                        onOcorrencias = {
                            viewModel.page = BottomNavItem.OcorrenciasButton.route
                        }

                    )

                }




        }
    }

}