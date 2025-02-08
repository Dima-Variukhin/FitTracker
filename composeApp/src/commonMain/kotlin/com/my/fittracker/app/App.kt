package com.my.fittracker.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.LoginGraph
        ) {
            navigation<Route.LoginGraph>(
                startDestination = Route.Login
            ) {
                composable<Route.Login> {

                }

            }
        }
    }
}