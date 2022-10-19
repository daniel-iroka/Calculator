package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

/** IMPORTANT NOTE!! CHANGING THE NAME FROM 'CalculatorOperation' to 'Whatever' is purely experimental and I might change it back if I succeed **/

sealed class CalculatorOperation(val symbol : String) {

    /** NOTE! These are the Standard AND Scientific Calculator Operations for two operands. **/
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
    /**  LATER I FIND THE ACTUAL NAME FOR THIS OPERATION **/
    object In : CalculatorOperation("In(")
    object Inv : CalculatorOperation("3.14159265")
    object Brackets : CalculatorOperation("()")

}