package com.android.calculator.model

import com.android.calculator.CalculatorOperation

/**   This is our CalculatorState. In JetPack compose, a state is basically any value that changes overtime. The "state" in this sense is the USER input values-
 *    to be calculated, basically the two values that are to be calculated on screen.  **/


data class CalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    // We put null because it can be in a state where the USER inputs no CalculatorOperation.
    val operation : CalculatorOperation? = null
)