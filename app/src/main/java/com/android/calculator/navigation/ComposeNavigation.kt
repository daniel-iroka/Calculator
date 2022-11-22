package com.android.calculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ComposeNavigation(
    navController: NavHostController,
) {

    // TODO - WHEN I COME BACK, I WILL FIRSTLY PROCEED WITH THE ADDING OF ANIMATION TO OUR NAVIGATION(rememberNavHostAnimationController)

    // We will try to Implement our NavHost here.
    NavHost(
        navController = navController ,
        startDestination = "main_screen",
    ) {

        composable("main_screen") {
            MainScreen(navController = navController)
        }

        composable("second_screen") {
            SecondScreen(navController = navController)
        }

        composable("third_screen") {
            ThirdScreen(navController = navController)
        }
    }
}