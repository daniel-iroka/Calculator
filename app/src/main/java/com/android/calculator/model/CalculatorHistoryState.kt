package com.android.calculator.model

/**
 * This is a Separate state for the Calculation results stored in our History Section.
 */

data class CalculatorHistoryState(
    val historyPrimaryState : String = "",
    val historySecondaryState : String = "",
    // TODO - Later I will properly format this to DateFormat.
    val time : String = ""
)
