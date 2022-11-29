package com.android.calculator.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.calculator.CalculatorHistory
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

@Composable
fun ThirdScreen(
    // I will need this later like when using the back button for example
    navController : NavHostController
) {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.historyState

    CalculatorHistory(
        state = state,
        onAction = viewModel::onActionForHistory,
        modifier = Modifier
            .fillMaxSize()
            .background(MediumGray)
            .padding(12.dp)
    )
}