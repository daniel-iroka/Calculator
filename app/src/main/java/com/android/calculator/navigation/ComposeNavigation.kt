package com.android.calculator.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.calculator.model.CalculatorHistoryState
import com.android.calculator.viewmodels.CalculatorViewModel
import com.android.calculator.viewmodels.ScientificCalculatorViewModel

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
    val strCalcViewModel = viewModel<CalculatorViewModel>()
    val sciCalcViewModel = viewModel<ScientificCalculatorViewModel>()

    val strCalcState = strCalcViewModel.strState
    val sciCalcState = sciCalcViewModel.sciState

    val strHistoryState = strCalcViewModel.historyState
    val sciHistoryState = sciCalcViewModel.historyState

    // This holds our current available 'HistoryState' based on where the Calculation was performed(Screens) by the USER.
//    var currHistory by remember { mutableStateOf(CalculatorHistoryState()) }
    // launchedEffect is a type of side-effect which in this case will execute the coroutine block whenever there has been a change in it's "key.
//    LaunchedEffect(key1 = strCalcState) {
//        if(strCalcState.secondaryTextState.isEmpty()) {
//            currHistory = strHistoryState
//        }
//    }
//
//    LaunchedEffect(key1 = sciCalcState) {
//        if(sciCalcState.secondaryTextState.isEmpty()) {
//            currHistory = sciHistoryState
//        }
//    }


//
//    /** NOTE! This is a Simple test by NANA to show me how LaunchedEffect works with Couroutines. **/
//    val s = rememberScaffoldState()
//    LaunchedEffect(key1 = Unit, block = {
//        strCalcViewModel.api().collectLatest {
//            s.snackbarHostState.showSnackbar(it)
//        }
//    } )


//
    val currHistory = listOf(
        CalculatorHistoryState(historyPrimaryState = "2 + 2", historySecondaryState = "4"),
        CalculatorHistoryState(historyPrimaryState = "2 + 6", historySecondaryState = "8"),
        CalculatorHistoryState(historyPrimaryState = "36 / 6", historySecondaryState = "2"),
        CalculatorHistoryState(historyPrimaryState = "7 - 6", historySecondaryState = "1")
    )

    NavHost(
        navController = navController,
        startDestination = "main_screen",
    ) {
        
        composable("main_screen") {
            MainScreen(
                navController = navController, state = strCalcState, viewModel = strCalcViewModel
            )
        }

        composable("first_screen") {
            FirstScreen(
                navController = navController, state = sciCalcState, viewModel = sciCalcViewModel
            )
        }

        composable("second_screen") {
            SecondScreen(
                navController = navController, historyState =  currHistory, viewModel = strCalcViewModel
            )
        }
    }
}


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