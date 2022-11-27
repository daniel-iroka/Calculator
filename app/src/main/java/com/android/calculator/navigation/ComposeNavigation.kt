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

    val springSec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
    val tweenSpec = tween<IntOffset>(durationMillis = 2000, easing = CubicBezierEasing(0.08f, 0.39f, 0.68f, 1.27f))

    // This is where we Implement out NavHost.

    AnimatedNavHost(
        navController = navController ,
        startDestination = "main_screen",
    ) {

        composable(
            "main_screen"
        // Todo - When I come back, I may add an animation here to see if it'll replace the popEnterTransition when re-entering the screen.
        // Todo - Later I may also experiment with other animation transitions until I am satisfied.
        ) {
            MainScreen(navController = navController)
        }
        composable(
            "second_screen",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 500 }, animationSpec = springSec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -500 }, animationSpec = springSec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -500 }, animationSpec = springSec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 500 }, animationSpec = springSec)
            }
        ) {
            SecondScreen(navController = navController)
        }

        composable("third_screen") {
            ThirdScreen(navController = navController)
        }
    }
}


//enterTransition = {_, _ ->
//    slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSec)
//},

//composable(
//"second_screen",
//enterTransition = {_, _ ->
//    slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSec)
//},
//exitTransition = { _, _ ->
//    slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSec)
//},
//popEnterTransition = { _, _ ->
//    slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSec)
//},
//popExitTransition = { _, _ ->
//    slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSec)
//}
//)

