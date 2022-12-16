package com.android.calculator.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.calculator.CalculatorHistory
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

@Composable
fun SecondScreen(
    navController : NavHostController
) {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    CalculatorHistory(
        state = state,
        onAction = viewModel::onAction,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(MediumGray)
    )
}