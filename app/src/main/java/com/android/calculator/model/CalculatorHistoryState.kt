package com.android.calculator.model

data class CalculatorHistoryState(
    val primaryState : String = "9 × 9",
    val secondaryState : String = "81",
    val time : String = "3 days ago"
)