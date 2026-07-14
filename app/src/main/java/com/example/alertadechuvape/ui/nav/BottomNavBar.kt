package com.example.alertadechuvape.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.alertadechuvape.viewmodel.MainViewModel
@Composable
fun BottomNavBar(
    viewModel: MainViewModel,
    navController: NavHostController,
    items: List<BottomNavItem>
){

    NavigationBar {

        val navBackStackEntry by
        navController.currentBackStackEntryAsState()

        val currentRoute =
            navBackStackEntry?.destination?.route

        items.forEach { item ->

            NavigationBarItem(

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },

                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp
                    )
                },

                selected =
                    viewModel.page == item.route,

                onClick = {
                    viewModel.page = item.route
                }
            )
        }
    }
}