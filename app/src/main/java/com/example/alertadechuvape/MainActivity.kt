package com.example.alertadechuvape

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

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AlertaDeChuvaPeTheme {

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
                var showDialog by remember {
                    mutableStateOf(false)
                }

                if (showDialog) {

                    OcorrenciaDialog(

                        onDismiss = {
                            showDialog = false
                        },

                        onConfirm = { tipo ->

                            if (tipo.isNotBlank()) {
                                viewModel.add(tipo)
                            }

                            showDialog = false
                        }
                    )
                }

                Scaffold(

                    topBar = {

                        TopAppBar(

                            title = {
                                Text("AlertaChuvaPE")
                            },

                            actions = {

                                Button(
                                    onClick = {
                                        finish()
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
                            viewModel = viewModel
                        )

                    }

                }

            }

        }
    }
}