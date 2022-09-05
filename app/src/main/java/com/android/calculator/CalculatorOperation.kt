package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

sealed class CalculatorOperation(val symbol : String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("x")
    object Divide : CalculatorOperation("/")
    object Modulo : CalculatorOperation("%")
}