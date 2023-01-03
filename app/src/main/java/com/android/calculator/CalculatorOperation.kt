package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

sealed class CalculatorOperation(val symbol : String) {

    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("×")
    object Divide : CalculatorOperation("÷")
    object Modulo : CalculatorOperation("%")

    object SquareRoot : CalculatorOperation("√")
    object Squared : CalculatorOperation("²")
    object Factorial : CalculatorOperation("!")

    object Sin : CalculatorOperation("sin(")
    object Cos : CalculatorOperation("cos(")
    object Tan : CalculatorOperation("tan(")
    object Log : CalculatorOperation("log(")
    object In : CalculatorOperation("ln(")
    object Inv : CalculatorOperation("3.14159265")
}