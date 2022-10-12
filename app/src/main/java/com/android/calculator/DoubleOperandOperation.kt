package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

sealed class CalculatorOperation(val symbol : String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("×")
    object Divide : CalculatorOperation("÷")
    object Modulo : CalculatorOperation("%")

    // Scientific Calculation Action
    object Sin : CalculatorOperation("sin(")
    object Cos : CalculatorOperation("cos(")
    object Tan : CalculatorOperation("tan(")
    object Log : CalculatorOperation("log(")
    // TODO - LATER I FIND THE ACTUAL NAME FOR THIS OPERATION
    object In : CalculatorOperation("In(")

    object Factorial : CalculatorOperation("x!")
    object Square : CalculatorOperation("²")
    object SquareRoot : CalculatorOperation("√")
    object Inv : CalculatorOperation("1/x")
    object Brackets : CalculatorOperation("()")
}