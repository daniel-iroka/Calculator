package com.android.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.android.calculator.navigation.ComposeNavigation
import com.android.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {

               // TODO - NOTE( "THIS IS A MAJOR TASK THAT I WILL ACCOMPLISH LATER IN THIS PROJECT WHICH IS THE DESIGNING OF THE LANDSCAPE ADAPTIVE LAYOUT OF THIS APP ONLY IF IT'S POSSIBLE AND NOT TO DIFFICULT")
                AppPreview()
            }
        }
    }
}


@Preview
@Composable
fun AppPreview() {

    // We are using a this because we want to add animation to our compose navigation.
    val navController = rememberNavController()
    ComposeNavigation(
        navController = navController,
        hiltViewModel()
    )
}