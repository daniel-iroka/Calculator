package com.android.calculator.model

import androidx.compose.ui.graphics.Color
import com.android.calculator.CalculatorOperation
import java.util.Date

/**   This is our CalculatorState. In JetPack compose, a state is basically any value that changes overtime. The "state" in this sense is the USER input values-
 *    to be calculated, basically the two values that are to be calculated on screen.  **/

data class CalculatorState(
    val color : Color = Color.White,
    val primaryTextState : String = "",
    val secondaryTextState : String = "",
    val historyPrimaryState : String = "",
    val historySecondaryState : String = "",
    val time : String = "",
    val operation : CalculatorOperation? = null,
)
