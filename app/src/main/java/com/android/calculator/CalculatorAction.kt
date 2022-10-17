package com.android.calculator


/** This Sealed class is for our Calculator Actions like deleting, clearing or calculating. **/

// A Sealed class in kotlin is basically a class that defines a set of subclasses in it like objects and the rest
sealed class CalculatorAction {
    data class Number(val number : Int) : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
    object Calculate  : CalculatorAction()
    data class Operation(val operation : CalculatorOperation) : CalculatorAction()
}
