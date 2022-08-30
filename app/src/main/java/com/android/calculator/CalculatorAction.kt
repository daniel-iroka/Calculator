package com.android.calculator

// TODO - LATER, FIND WHAT A SEALED CLASS IS IN KOTLIN.

/** This Sealed clas is for our Calculator Actions like deleting, clearing or calculating. **/

sealed class CalculatorAction {
    data class Number(val number : Int) : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
    object Calculate  : CalculatorAction()
    data class Operation(val operation : CalculatorOperation) : CalculatorAction()
}
