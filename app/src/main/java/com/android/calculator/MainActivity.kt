package com.android.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.calculator.ui.theme.CalculatorTheme
import com.android.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {

                TODO("THIS IS A MAJOR TASK THAT I WILL ACCOMPLISH LATER IN THIS PROJECT WHICH IS THE DESIGNING OF THE LANDSCAPE ADAPTIVE LAYOUT OF THIS APP.")

            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
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