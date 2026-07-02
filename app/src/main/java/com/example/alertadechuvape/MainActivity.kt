package com.example.alertadechuvape

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.alertadechuvape.ui.nav.BottomNavBar
import com.example.alertadechuvape.ui.nav.MainNavHost
import com.example.alertadechuvape.ui.theme.AlertaDeChuvaPeTheme
import androidx.navigation.compose.rememberNavController
import com.example.alertadechuvape.ui.nav.BottomNavItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.activity.viewModels
import com.example.alertadechuvape.viewmodel.MainViewModel
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.alertadechuvape.ui.OcorrenciaDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alertadechuvape.api.WeatherService
import com.example.alertadechuvape.db.fb.FBDatabase
import com.example.alertadechuvape.ui.OcorrenciaMapaDialog
import com.example.alertadechuvape.viewmodel.MainViewModelFactory
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AlertaDeChuvaPeTheme {
                val fbDB = remember {
                    FBDatabase()
                }

                val weatherService = remember {
                    WeatherService()
                }

                val viewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(
                        fbDB,
                        weatherService
                    )
                )

                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState()

                val showButton =
                    currentRoute.value?.destination?.route ==
                            BottomNavItem.OcorrenciasButton.route
                val launcher =
                    rememberLauncherForActivityResult(
                        contract =
                            ActivityResultContracts.RequestPermission(),
                        onResult = {}
                    )
                var localSelecionado by remember {
                    mutableStateOf<LatLng?>(null)
                }

                var cidadeSelecionada by remember {
                    mutableStateOf("")
                }

                var showDialog by remember {
                    mutableStateOf(false)
                }

                if (showDialog) {
                    if (localSelecionado == null){
                        OcorrenciaDialog(
                            onDismiss = {
                                showDialog = false
                                localSelecionado = null
                                cidadeSelecionada = ""
                            },
                            onConfirm = { tipo, cidade, descricao ->

                                viewModel.addOcorrencia(
                                    tipo = tipo,
                                    cidade = cidade,
                                    descricao = descricao
                                )

                                showDialog = false
                            }
                        )


                    } else {
                        OcorrenciaMapaDialog(
                            onDismiss = {
                                showDialog = false
                                localSelecionado = null
                                cidadeSelecionada = ""
                            },
                            onConfirm = { tipo, descricao ->

                                localSelecionado?.let { local ->

                                    viewModel.addOcorrencia(
                                        tipo = tipo,
                                        cidade = cidadeSelecionada,
                                        descricao = descricao,
                                        local = local
                                    )

                                }

                                showDialog = false
                                localSelecionado = null
                                cidadeSelecionada = ""
                            }
                        )

                    }


                }

                Scaffold(

                    topBar = {

                        TopAppBar(

                            title = {

                                val nome =
                                    viewModel.user?.name ?: "[carregando...]"

                                Text("Bem-vindo(a)! $nome")

                            },

                            actions = {

                                Button(
                                    onClick = {
                                        Firebase.auth.signOut()
                                    }
                                ) {
                                    Text("Sair")
                                }

                            }

                        )

                    },

                    bottomBar = {

                        val items = listOf(

                            BottomNavItem.HomeButton,
                            BottomNavItem.OcorrenciasButton,
                            BottomNavItem.MapButton
                        )

                        BottomNavBar(
                            navController = navController,
                            items = items
                        )

                    },

                    floatingActionButton = {

                        if (showButton) {

                            FloatingActionButton(
                                onClick = {
                                    showDialog = true
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Adicionar"
                                )

                            }

                        }

                    }
                ) { padding ->

                    Box(
                        modifier = Modifier.padding(padding)
                    ) {

                        launcher.launch(
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        )

                        MainNavHost(
                            navController = navController,
                            viewModel = viewModel,
                            onMapClick = { latLng ->

                                localSelecionado = latLng

                                viewModel.buscarNomeCidade(latLng) { cidade ->

                                    cidadeSelecionada = cidade
                                    showDialog = true

                                }

                            }
                        )

                    }

                }

            }

        }
    }
}