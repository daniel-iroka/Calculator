package com.android.calculator.navigation

import android.util.Log
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.calculator.CalculatorOperation
import com.android.calculator.model.CalculatorHistoryState
import com.android.calculator.viewmodels.CalculatorViewModel
import kotlinx.coroutines.flow.collectLatest

/**
 *  This is our Compose NavGraph and where we Implement our NavHost.
 */

private const val TAG = "ComposeNavigation"

@Composable
fun ComposeNavigation(
    navController: NavHostController,
) {
    /**
     *  Here We declare an Instance of our Two ViewModels, their states and History States. This is because we don't want to have the same States for the two Screens.
     */
    val viewModel = viewModel<CalculatorViewModel>()

    val strCalcState = viewModel.strState
    val sciCalcState = viewModel.sciState

    val currentHistory = viewModel.historyState

//    val currentHistory = listOf(
//        CalculatorHistoryState(historySecondaryState = "40", historyPrimaryState = "8 * 5"),
//        CalculatorHistoryState(historySecondaryState = "225", historyPrimaryState = "15 * 15"),
//        CalculatorHistoryState(historySecondaryState = "20", historyPrimaryState = "10 + 10"),
//        CalculatorHistoryState(historySecondaryState = "40", historyPrimaryState = "90 - 50")
//    )

    LaunchedEffect(key1 = viewModel.historyState, block = {
        Log.i(TAG, "Our current history size list is ${viewModel.historyState.size}")

        Log.i(TAG, "And our HistoryState is ${viewModel.historyState}")
    })

    /** NOTE! This is a Simple test by NANA to show me how LaunchedEffect works with Couroutines. **/
    val s = rememberScaffoldState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.api().collectLatest {
            s.snackbarHostState.showSnackbar(it)
        }
    })

    NavHost(
        navController = navController,
        startDestination = "main_screen",
    ) {
        
        composable("main_screen") {
            MainScreen(
                navController = navController, state = strCalcState, viewModel = viewModel
            )
        }

        composable("first_screen") {
            FirstScreen(
                navController = navController, state = sciCalcState, viewModel = viewModel
            )
        }

        composable("second_screen") {
            SecondScreen(
                navController = navController, historyState =  currentHistory, viewModel = viewModel
            )
        }
    }
}


// This holds our current available 'HistoryState' based on where the Calculation was performed(Screens) by the USER.
// launchedEffect is a type of side-effect which in this case will execute the coroutine block whenever there has been a change in it's "key.
//    val currHistory = listOf(
//        LaunchedEffect(key1 = strCalcState) {
//            if(strCalcState.secondaryTextState.isEmpty()) {
//                strHistoryState
//            }
//        },
//
//        LaunchedEffect(key1 = sciCalcState) {
//            if(sciCalcState.secondaryTextState.isEmpty()) {
//                sciHistoryState
//            }
//        }
//    )


//Log.i(TAG,"Current history in ComposeNavigation class is : ${clearHistory.currentHistoryState}")

//    Log.i(TAG, "This is our History State in our Compose Navigation : ${currHistoryViewModel.currentHistoryState}")




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

//I  This is our currentHistory State in our Compose Navigation : CalculatorHistoryState(historyPrimaryState=9×7, historySecondaryState=63.0, time=3 days ago)
//I  This is our currentHistory State in our ViewModel : CalculatorHistoryState(historyPrimaryState=, historySecondaryState=, time=3 days ago)
//I  This is our currentHistory State in our History Screen : 9×7 and 63.0