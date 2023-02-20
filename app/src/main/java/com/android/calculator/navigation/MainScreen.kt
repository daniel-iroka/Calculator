package com.android.calculator.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.calculator.models.CalculatorState
import com.android.calculator.ui.composables.Calculator
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

/**
 * This is our FirstScreen that holds our Scientific Calculator and it will be the default or firstScreen.
 */

private const val TAG = "MainScreen"

@Composable
fun MainScreen(
    navController : NavHostController,
    state : CalculatorState,
    viewModel : CalculatorViewModel
) {

    val buttonSpacing = 8.dp

    // Calling our Compose file in the theme. This will automatically inflate the Compose UI elements
    Calculator(
        state = state,
        onAction = viewModel::onActionForStandardOpr,
        buttonSpacing = buttonSpacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize() // will fill up the entire screen
            .background(MediumGray)
            .padding(12.dp)
    )
    // This was added here in order to check the instance 'ID' of our ViewModel to know if it is the same
    Log.i(TAG, "Our ViewModel instance Identifier for Main Screen is : ${viewModel.hashCode()}")  // OR viewModel.toString()
}