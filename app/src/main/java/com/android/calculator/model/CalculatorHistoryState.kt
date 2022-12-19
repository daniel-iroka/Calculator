package com.android.calculator.model

/**
 * This is a Separate state for the Calculation results stored in our History Section.
 */

data class CalculatorHistoryState(
    val historyPrimaryState : String = "",
    val historySecondaryState : String = "",
    // TODO - When I come back Later I will check how to assign actual time with the format of 'Today', 'Yesterday', '2 Days ago' etc.
    val time : String = "3 days ago"
)
