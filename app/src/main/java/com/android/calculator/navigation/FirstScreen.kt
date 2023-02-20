package com.android.calculator.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.calculator.models.ScientificCalculatorState
import com.android.calculator.ui.composables.ScientificCalculator
import com.android.calculator.ui.theme.MediumGray
import com.android.calculator.viewmodel.CalculatorViewModel

private const val TAG = "FirstScreen"

@Composable
fun FirstScreen(
    navController : NavHostController,
    state : ScientificCalculatorState,
    viewModel : CalculatorViewModel
) {

    val buttonSpacing = 8.dp

    ScientificCalculator(
        state = state,
        onAction = viewModel::onActionForScientificOpr,
        buttonSpacing = buttonSpacing,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(MediumGray)
            .padding(12.dp)
    )
    Log.i(TAG, "Our ViewModel instance Identifier for First Screen is ${viewModel.hashCode()}")
}