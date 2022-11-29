package com.android.calculator.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ComposeNavigation(
    navController: NavHostController,
) {

    // TODO - WHEN I COME BACK TOMORROW, I WILL CONTINUE EXPERIMENTING ON ANIMATIONS TO SEE WHICH ONE FITS THE APP BETTER

    val springSec = spring<IntOffset>(dampingRatio = Spring.StiffnessVeryLow)
    val tweenSpec = tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f, 0.39f, 0.68f, 1.27f))

    // This is where we Implement out NavHost.

    AnimatedNavHost(
        navController = navController ,
        startDestination = "main_screen",
    ) {

        composable(
            "main_screen",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -2000 }, animationSpec = springSec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 2000 }, animationSpec = springSec)
            }

        ) {
            MainScreen(navController = navController)
        }
        composable(
            // Todo - When I come back, I will continue testing the speed of this animations and continue experimenting on the animations themselves.
            "second_screen",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 5000 }, animationSpec = springSec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -5000 }, animationSpec = springSec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -3000 }, animationSpec = springSec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 3000 }, animationSpec = springSec)
            }
        ) {
            SecondScreen(navController = navController)
        }

        composable("third_screen") {
            ThirdScreen(navController = navController)
        }
    }
}