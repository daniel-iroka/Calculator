package com.android.calculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ComposeNavigation(
    navController: NavHostController,
) {
    // We will try to Implement our NavHost here.
    NavHost(
        navController = navController ,
        startDestination = "first_screen",
    ) {

        composable("first_screen") {
            FirstScreen(navController = navController)
        }

        composable("second_screen") {
            SecondScreen(navController = navController)
        }
    }
}