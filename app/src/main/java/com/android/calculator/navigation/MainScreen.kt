package com.android.calculator.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.calculator.ui.Calculator
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

/** This is our FirstScreen that holds our Scientific Calculator and it will be the default or firstScreen. **/

@Composable
fun MainScreen(
    navController : NavHostController
) {

    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp

    // Calling our Compose file in the theme. This will automatically inflate the Compose UI elements
    Calculator(
        state = state,
        onAction = viewModel::onAction,
        buttonSpacing = buttonSpacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize() // will fill up the entire screen
            .background(MediumGray)
            .padding(12.dp)
    )
}