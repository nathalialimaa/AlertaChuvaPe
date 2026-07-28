package com.example.alertadechuvape.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertadechuvape.ui.components.ActionButtons
import com.example.alertadechuvape.ui.components.CardAlerta
import com.example.alertadechuvape.ui.components.CardClima
import com.example.alertadechuvape.ui.components.CardLocalizacao
import com.example.alertadechuvape.ui.components.CardMapa
import com.example.alertadechuvape.ui.components.CardPrevisao
import com.example.alertadechuvape.ui.components.CardResumo
import com.example.alertadechuvape.viewmodel.MainViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.NotificationsOff
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon

@Composable
fun HomePage(
    viewModel: MainViewModel
) {
    val weather = viewModel.weather
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {


        CardLocalizacao(viewModel.localizacao)

        val monitorando =

            viewModel.user?.monitoramentoAtivo ?: false

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.End

        ) {

            Icon(

                imageVector =

                    if (monitorando)

                        Icons.Filled.Notifications

                    else

                        Icons.Outlined.NotificationsOff,

                contentDescription = null,

                modifier = Modifier

                    .size(32.dp)

                    .clickable {

                        viewModel.alterarMonitoramento(

                            !monitorando

                        )

                    }

            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        CardMapa()

        Spacer(modifier = Modifier.height(16.dp))

        CardClima(viewModel.weather)

        CardPrevisao(viewModel)

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        CardAlerta()

        Spacer(modifier = Modifier.height(16.dp))

        CardResumo(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        ActionButtons()

    }

}