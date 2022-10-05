package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

sealed class CalculatorOperation(val symbol : String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("×")
    object Divide : CalculatorOperation("÷")
    object Modulo : CalculatorOperation("%")

    // Scientific Calculation Action
//    object Sin : CalculatorOperation("sin(")
//    object Cos : CalculatorOperation("cos(")
//    object Tan : CalculatorOperation("tan(")
//    object Log : CalculatorOperation("log(")
//    object In : CalculatorOperation("In(")
//
//    /** IMPORTANT ! I will find the actual names of some of this symbols but for now, I will give them this temporary names..
//     *              I will also find or research how some of this symbols are actually displayed when they're keyed into the screen. **/
//    object x1 : CalculatorOperation("x!")
//    object x2 : CalculatorOperation("²")
//    object Sqaured : CalculatorOperation("√")
//    object X1 : CalculatorOperation("1/X")
//    object Brackets : CalculatorOperation("()")
}