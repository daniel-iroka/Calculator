package com.android.calculator.model

import androidx.compose.ui.graphics.Color
import com.android.calculator.CalculatorOperation

/** This is a test State for our Scientific Calculator. **/

data class ScientificCalculatorState(
    val color : Color = Color.White,
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val operation : CalculatorOperation? = null,
)