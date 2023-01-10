package com.android.calculator.model

import java.util.*

/*
 * This is a Separate state for the Calculation results stored in our History Section.
 */

data class CalculatorHistoryState(
    val historyPrimaryState : String = "",
    val historySecondaryState : String = "",
    val time : Date = Date()
)
