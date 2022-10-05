package com.android.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.android.calculator.navigation.ComposeNavigation
import com.android.calculator.ui.theme.CalculatorTheme

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

/** IMPORTANT! - I will still leave this preview here here quick testing, but may be removed later during the final release of this App. **/
@Preview
@Composable
fun AppPreview() {

    val navController = rememberNavController()
    ComposeNavigation(
        navController = navController
    )
}