package com.android.calculator.models

import androidx.compose.ui.graphics.Color
import com.android.calculator.CalculatorOperation

/** This is the State for our Scientific Calculator. **/

data class ScientificCalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val primaryTextColor : Color = Color.White,
    val secondaryTextColor : Color = Color.LightGray,
    val operation : CalculatorOperation? = null,
)