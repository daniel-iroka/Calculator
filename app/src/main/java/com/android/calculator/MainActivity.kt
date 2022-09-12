package com.android.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.calculator.ui.theme.CalculatorTheme
import com.android.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp

                // Calling our Compose file in the theme. This will automatically inflate the Compose UI elements
                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize() // this will take up the entire size of the screen
                        .background(MediumGray)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun ShowOverFlowMenu() {
    OverFlowMenu {
        // TODO : WHEN I COME BACK, I WILL PROCEED WITH ADDING AN OVERFLOW MENU WITHOUT THE APP BAR SHOWING TO ENABLE US ACCESS SETTINGS LIKE-
        // TODO : CHANGE THE COLOR AND CHANGE THE CALCULATOR TYPE TO SCIENTIFIC AND STANDARD.
    }
}