package com.example.alertadechuvape

import android.content.Intent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.alertadechuvape.ui.nav.BottomNavBar
import com.example.alertadechuvape.ui.nav.MainNavHost
import com.example.alertadechuvape.ui.theme.AlertaDeChuvaPeTheme
import androidx.navigation.compose.rememberNavController
import com.example.alertadechuvape.ui.nav.BottomNavItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alertadechuvape.api.WeatherService
import com.example.alertadechuvape.db.fb.FBDatabase
import com.example.alertadechuvape.ui.OcorrenciaMapaDialog
import com.example.alertadechuvape.viewmodel.MainViewModelFactory
import com.google.android.gms.maps.model.LatLng
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import com.example.alertadechuvape.monitor.ForecastMonitor
import com.google.android.gms.location.LocationServices
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Firebase.auth.currentUser == null) {

            startActivity(
                Intent(this@MainActivity, WelcomeActivity::class.java)
            )

            finish()

        }
        setContent {



            AlertaDeChuvaPeTheme {
                val fbDB = remember {
                    FBDatabase()
                }

                val weatherService = remember {
                    WeatherService()
                }
                val forecastMonitor = remember {

                    ForecastMonitor(this)

                }
                val viewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(
                        fbDB,
                        weatherService,
                        forecastMonitor
                    )
                )

                DisposableEffect(Unit) {

                    val listener = androidx.core.util.Consumer<Intent> {

                        viewModel.page = BottomNavItem.HomeButton.route

                    }

                    addOnNewIntentListener(listener)

                    onDispose {
                        removeOnNewIntentListener(listener)
                    }

                }


                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState()

                val showButton =
                    currentRoute.value?.destination?.route ==
                            BottomNavItem.OcorrenciasButton.route
                val launcher =
                    rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestMultiplePermissions()
                    ) { }
                var localSelecionado by remember {
                    mutableStateOf<LatLng?>(null)
                }

                var cidadeSelecionada by remember {
                    mutableStateOf("")
                }
                /*                LaunchedEffect(Unit) {
                    viewModel.carregarCidadeAtual()
                }
*/
                var showDialog by remember {
                    mutableStateOf(false)
                }

                if (showDialog) {
                    if (localSelecionado == null) {
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
                                viewModel.page = BottomNavItem.HomeButton.route

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

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(R.drawable.fundo_home),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Scaffold(

                        containerColor = Color.Transparent,

                        topBar = {

                            if (viewModel.page == BottomNavItem.HomeButton.route) {

                                TopAppBar(

                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Transparent
                                    ),

                                    title = {

                                        Image(
                                            painter = painterResource(R.drawable.logo_horizontal),
                                            contentDescription = null,
                                            modifier = Modifier.height(100.dp)
                                        )

                                    },

                                    actions = {

                                        Button(
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF012C9B),
                                                contentColor = Color.White
                                            ),
                                            onClick = {
                                                viewModel.logout()
                                            }
                                        ) {
                                            Text("Sair")
                                        }

                                    }

                                )

                            }

                        },

                        bottomBar = {

                            val items = listOf(

                                BottomNavItem.HomeButton,
                                BottomNavItem.OcorrenciasButton,
                                BottomNavItem.MapButton
                            )

                            BottomNavBar(
                                viewModel = viewModel,
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
                                arrayOf(
                                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                                    android.Manifest.permission.POST_NOTIFICATIONS
                                )
                            )
                            val context = LocalContext.current

                            LaunchedEffect(Unit) {

                                val client =
                                    LocationServices.getFusedLocationProviderClient(context)

                                try {

                                    client.lastLocation.addOnSuccessListener { location ->

                                        if (location != null) {

                                            viewModel.carregarCidadeAtual(

                                                context,

                                                LatLng(

                                                    location.latitude,
                                                    location.longitude

                                                )

                                            )

                                        }

                                    }

                                } catch (_: SecurityException) {

                                }

                            }


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
                            LaunchedEffect(viewModel.page) {

                                navController.navigate(viewModel.page) {

                                    popUpTo(
                                        navController.graph.startDestinationId
                                    ) {
                                        saveState = true
                                    }

                                    restoreState = true

                                    launchSingleTop = true

                                }

                            }

                        }

                    }

                }

            }
        }
    }
}