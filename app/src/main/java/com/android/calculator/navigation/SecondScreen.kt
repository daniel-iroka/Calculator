package com.android.calculator.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.calculator.CalculatorViewModel
import com.android.calculator.ScientificCalculator
import com.android.calculator.ui.theme.MediumGray

/** This is our SecondScreen that holds our Scientific Calculator and it will be the Second. **/

@Composable
fun SecondScreen(
    navController : NavHostController,
) {

    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 8.dp

    ScientificCalculator(
        state = state,
        onAction = viewModel::onAction,
        buttonSpacing = buttonSpacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()  // this will take up the entire space of the screen
            .background(MediumGray)
            .padding(12.dp)
    )
}