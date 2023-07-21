package com.android.calculator.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.android.calculator.db.entity.CalculatorEntity
import com.android.calculator.models.CalculatorHistoryState
import com.android.calculator.ui.composables.HistoryScreen
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

@Composable
fun SecondScreen(
    navController: NavHostController,
    historyState: List<CalculatorHistoryState>,
    viewModel: CalculatorViewModel
) {

    HistoryScreen(
        state = historyState,
        onAction = viewModel::onActionForStandardOpr,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(MediumGray)
    )
}