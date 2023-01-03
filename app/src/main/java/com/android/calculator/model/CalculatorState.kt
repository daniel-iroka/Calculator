package com.android.calculator.model

import androidx.compose.ui.graphics.Color
import com.android.calculator.CalculatorOperation

/**
 *  This is our CalculatorState. In JetPack compose, a state is basically any value that changes overtime. The "state" in this sense is the USER input values-
 */

data class CalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val primaryTextColor : Color = Color.White,
    val secondaryTextColor : Color = Color.LightGray,
    val operation : CalculatorOperation? = null,
)
