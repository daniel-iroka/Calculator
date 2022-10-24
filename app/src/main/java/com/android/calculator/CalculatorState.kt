package com.android.calculator

/**   This is our CalculatorState. In JetPack compose, a state is basically any value that changes overtime. The "state" in this sense is the USER input values-
 *    to be calculated, basically the two values that are to be calculated on screen.  **/

// This is kind of like our Model class I guess but in compose we call it state
data class CalculatorState(
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    // We put null because it can be in a state where the USER inputs no CalculatorOperation.
    val operation : CalculatorOperation? = null
)