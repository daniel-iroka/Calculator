package com.android.calculator

sealed class SingleOperandOperation(val symbol : String) {
    object Factorial : SingleOperandOperation("x!")
    object Squared : SingleOperandOperation("²")
    object SquareRoot : SingleOperandOperation("√")

    // Scientific Calculation Action
    /** IMPORTANT NOTE! I MAY TEMPORARILY LEAVE THIS OBJECTS HERE BECAUSE I MIGHT HAVE TO REMOVE THEM LATER. I DUNNO **/
    object Sin : SingleOperandOperation("sin(")
    object Cos : SingleOperandOperation("cos(")
    object Tan : SingleOperandOperation("tan(")
    object Log : SingleOperandOperation("log(")
    /**  LATER I FIND THE ACTUAL NAME FOR THIS OPERATION **/
    object In : SingleOperandOperation("In(")
    object Inv : SingleOperandOperation("1/x")
    object Brackets : SingleOperandOperation("()")
}