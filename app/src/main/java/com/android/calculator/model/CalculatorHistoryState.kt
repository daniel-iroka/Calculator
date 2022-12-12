package com.android.calculator.model

data class CalculatorHistoryState(
    val primaryState : String = "10 × 9",
    val secondaryState : String = "90",
    val time : String = "3 days ago"
)