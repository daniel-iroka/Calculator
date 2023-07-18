package com.android.calculator.models

import androidx.compose.ui.graphics.Color
import com.android.calculator.CalculatorOperation

/*
 *  This is where we put Calculator and Scientific Models(States because of compose)
 */

data class CalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val primaryTextColor : Color = Color.White,
    val secondaryTextColor : Color = Color.LightGray,
    val operation : CalculatorOperation? = null,
)

data class ScientificCalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val primaryTextColor : Color = Color.White,
    val secondaryTextColor : Color = Color.LightGray,
    val operation : CalculatorOperation? = null,
)