package com.android.calculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun ComposeNavigation(
    navController: NavHostController,
) {

    // This is where we Implement out NavHost

    NavHost(
        navController = navController,
        startDestination = "main_screen",
    ) {
        
        composable("main_screen") {
            MainScreen(navController = navController)
        }

        composable("first_screen") {
            FirstScreen(navController = navController)
        }

        composable("second_screen") {
            SecondScreen(navController = navController)
        }
    }
}







/** IMPORTANT NOTE! When I am really done wih this project and I want to still add animations to the screen transitions, I will come back to all this code. **/

//val springSec = spring<IntOffset>(dampingRatio = Spring.DampingRatioNoBouncy)
//val tweenSpec = tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f, 0.39f, 0.68f, 1.27f))

//,
//enterTransition = {
//    slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSec)
//},
//exitTransition = {
//    slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSec)
//},
//popEnterTransition = {
//    slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSec)
//},
//popExitTransition = {
//    slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSec)
//}

