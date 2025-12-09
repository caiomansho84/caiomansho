package com.example.caiomansho.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()) {

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onSuccess = { navController.navigate("home") }
            )
        }
        composable("home") {
            Text("Bem-vindo!")
        }
    }
}