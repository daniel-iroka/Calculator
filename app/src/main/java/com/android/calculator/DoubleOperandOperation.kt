package com.android.calculator

/** This Sealed Class is for our Basic Calculator Operations like adding, subtracting etc. **/

/** IMPORTANT NOTE!! CHANGING THE NAME FROM 'DoubleOperandOperation' to 'Whatever' is purely experimental and I might change it back if I succeed **/

sealed class DoubleOperandOperation(val symbol : String) {

    /** NOTE! These are the Standard AND Scientific Calculator Operations for two operands. **/
    object Add : DoubleOperandOperation("+")
    object Subtract : DoubleOperandOperation("-")
    object Multiply : DoubleOperandOperation("×")
    object Divide : DoubleOperandOperation("÷")
    object Modulo : DoubleOperandOperation("%")

    object SquareRoot : DoubleOperandOperation("√")
    object Squared : DoubleOperandOperation("²")


    /** TEST **/
    object Factorial : DoubleOperandOperation("x!")
    object Sin : DoubleOperandOperation("sin(")
    object Cos : DoubleOperandOperation("cos(")
    object Tan : DoubleOperandOperation("tan(")
    object Log : DoubleOperandOperation("log(")
    /**  LATER I FIND THE ACTUAL NAME FOR THIS OPERATION **/
    object In : DoubleOperandOperation("In(")
    object Inv : DoubleOperandOperation("1/x")
    object Brackets : DoubleOperandOperation("()")

}