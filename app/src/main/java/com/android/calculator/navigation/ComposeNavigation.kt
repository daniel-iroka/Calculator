package com.android.calculator.navigation

import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.calculator.viewmodel.CalculatorViewModel

/**
 *  This is our Compose NavGraph and where we Implement our NavHost.
 */

private const val TAG = "ComposeNavigation"

@Composable
fun ComposeNavigation(
    navController: NavHostController,
    viewModel : CalculatorViewModel
) {
    /**
     *  Here We declare an Instance of our Two ViewModels, their states and History States. This is because we don't want to have the same States for the two Screens.
     */

    val strCalcState = viewModel.strState
    val sciCalcState = viewModel.sciState

    val currentHistory = viewModel.historyState

    LaunchedEffect(key1 = viewModel.historyState, block = {
        Log.i(TAG, "Our current history size list is ${viewModel.historyState.size}")

        Log.i(TAG, "Our current HistoryState is ${viewModel.historyState}")
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
                navController = navController, historyState = currentHistory, viewModel = viewModel
            )
        }
    }
}

// This is our dummyList. We will use this for a series of specific testings.
//    val currentHistory = listOf(
//        CalculatorHistoryState(historySecondaryState = "60", historyPrimaryState = "30 + 30"),
//        CalculatorHistoryState(historySecondaryState = "0", historyPrimaryState = "20 x 0"),
//        CalculatorHistoryState(historySecondaryState = "30", historyPrimaryState = "6 x 6"),
//        CalculatorHistoryState(historySecondaryState = "10", historyPrimaryState = "5 + 5"),
//        CalculatorHistoryState(historySecondaryState = "30", historyPrimaryState = "6 x 5"),
//        CalculatorHistoryState(historySecondaryState = "9", historyPrimaryState = "15 + 6")
//    )

