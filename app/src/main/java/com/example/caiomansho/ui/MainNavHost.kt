package com.example.caiomansho.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBackArrow = currentDestination?.route == "transfer/{personId}"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("App") },
                navigationIcon = {
                    if (shouldShowBackArrow) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        onSuccess = { navController.navigate("home") }
                    )
                }
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable(
                    route = "transfer/{personId}",
                    arguments = listOf(navArgument("personId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val personId = backStackEntry.arguments?.getString("personId")
                    TransferScreen(personId = personId!!)
                }
            }
        }
    }
}