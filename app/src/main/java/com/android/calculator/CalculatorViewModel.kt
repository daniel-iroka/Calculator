package com.android.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.*

/** This is our ViewModel and in Jetpack compose, it is responsible for handling the User actions and click events as well as state in compose.
 *  It will also be responsible for handling UI rotation. **/


class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        // This means that we can change the state from the outside but we can still read it
        private set


    // So this function is where and how we will register our click events based on how we set in the Calculator Composable. Which basically means what will happen-
    // when the User clicks on the buttons or anything set in our Calculator Composable.
    fun onAction(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState() // will clear everything
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Brackets -> enterBrackets()
        }
    }

    // We are Basically making the click events possible by modifying the 'state'

    private fun performCalculation() {
        val primaryState = state.primaryTextState
        val value = evaluate(primaryState)
        val fValue = value.toString()
        state = state.copy(
            primaryTextState = fValue
        )

        state = state.copy(secondaryTextState = "")
    }

    private fun performDeletion() {
        when {
            state.primaryTextState.isNotBlank() -> state = state.copy(
                primaryTextState = state.primaryTextState.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        when(state.operation) {
            is CalculatorOperation.Add -> add(operation)
            is CalculatorOperation.Subtract -> subtract(operation)
            is CalculatorOperation.Multiply -> multiply(operation)
            is CalculatorOperation.Divide -> divide(operation)
            is CalculatorOperation.Modulo -> modulo(operation)
            is CalculatorOperation.SquareRoot -> squareRoot(operation)
            is CalculatorOperation.Squared -> squared(operation)
            is CalculatorOperation.Factorial -> factorial(operation)
            is CalculatorOperation.Sin -> sin(operation)
            is CalculatorOperation.Cos -> cos(operation)
            is CalculatorOperation.Tan -> tan(operation)
            is CalculatorOperation.Log -> log(operation)
            is CalculatorOperation.In -> ln(operation)
            is CalculatorOperation.Inv -> inv(operation)
            null -> return
        }
    }

    private fun subtract(operation: CalculatorOperation) {
        val str = state.primaryTextState
        if (!str.get(index = str.length - 1).equals("-")) {
            state = state.copy(operation = operation)
        }
    }

    private fun multiply(operation: CalculatorOperation) {
        val tvState = state.primaryTextState
        if (tvState.get(index = tvState.length - 1).equals("×")) {
            state = state.copy(operation = operation)
        }
    }

    private fun squareRoot(operation: CalculatorOperation) {
        if (state.primaryTextState.isEmpty()) {

            return // I'm not so sure about this, but I think it will likely return nothing
        } else {
            val result = sqrt(state.primaryTextState.toDouble())
            val calculatedResult = result.toString()
            state = state.copy(
                primaryTextState = calculatedResult
            )
        }
    }

    private fun squared(operation: CalculatorOperation) {
        if (state.primaryTextState.isEmpty()) {
            return
        } else {
            val result = state.primaryTextState.toDouble()
            val squaredResult = result * result
            state = state.copy(
                primaryTextState = squaredResult.toString()
            )

            state = state.copy(
                secondaryTextState = squaredResult.toString()
            )
        }
    }

    private fun factorial(operation: CalculatorOperation) {
        if (state.primaryTextState.isEmpty()) {
            return
        } else {
            val value = factorial(state.primaryTextState.toInt())
            state = state.copy(
                primaryTextState = value.toString()
            )

            state = state.copy(
                secondaryTextState = value.toString()
            )
        }
    }

    private fun add(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun divide(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun modulo(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun sin(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun cos(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun tan(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun log(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun ln(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun inv(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }


    private fun enterDecimal() {
        // A reminder that this will only execute when == true
        if (state.operation == null && !state.primaryTextState.contains(".")
            && state.primaryTextState.isNotBlank()
        ) {
            state = state.copy(
                primaryTextState = state.primaryTextState + "."
            )
            return
        }
    }

    private fun enterBrackets() {
        val tvState = state.primaryTextState
        // TODO NOTE! LATER I WILL WORK ON THIS BRACKET
        state = if (!tvState.contains("(") || tvState.contains(")")) {
            state.copy(
                primaryTextState = state.primaryTextState + "("
            )
        } else {
            state.copy(
                primaryTextState = state.primaryTextState + ")"
            )
        }
    }

    private fun enterNumber(number: Int) {
        /** NOTE! I removed the 'if' check that checks if the state.operation is null before entering a number. **/
        if (state.primaryTextState.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            primaryTextState = state.primaryTextState + number
        )
    }

    // Our factorial function
    private fun factorial(num : Int) : Long {
        return if (num >= 1)
            num * factorial(num - 1)
         else
            1
    }

    // this is the function to perform our Calculation
    private fun evaluate(value : String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos <value.length)value[pos].toInt()else -1
            }

            fun eat(charToEdt: Int): Boolean {
                while (ch== ' '.toInt())nextChar()

                if (ch==charToEdt){
                    nextChar()
                    return true
                }
                return false
            }

            fun parse() : Double {
                nextChar()
                val x = parseExpression()
                if(pos<value.length) throw RuntimeException("Unexpected : "+ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt()))x +=parseTerm()
                    else if (eat('-'))
                        // TODO - WHEN I COME BACK, I WILL CONTINUE THIS.
                }
            }

            fun parseTerm(): Double {
                var x =
            }
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}







////////////////////////////////////// THIS ARE ALL THE CODES I WILL PUT BELOW DURING THIS REFACTOR /////////////////////////////////
// For our second state(Decimal function)
//if (!state.number2.contains(".") && state.number2.isNotBlank()
//) {
//    state = state.copy(
//        number2 = state.number2 + "."
//    )
//}


//(enterNumber function)
//if (state.number2.length >= MAX_NUM_LENGTH) {
//    return
//}
//state = state.copy(
//number2 = state.number2 + number
//)


//state.number2.isNotBlank() -> state = state.copy(
//// will drop the last digit of the value entered
//number2 = state.number2.dropLast(1)
//)

//
//// Calculating the result of our standard and Scientific Calculator operations that have two operands
//if (number1 != null && number2 != null){
//    val result = when(state.operation) {
//        is CalculatorOperation.Add -> number1 + number2
//        is CalculatorOperation.Subtract -> number1 - number2
//        is CalculatorOperation.Multiply -> number1 * number2
//        is CalculatorOperation.Divide -> number1 / number2
//        is CalculatorOperation.Modulo -> number1 % number2
//
//        /** Operations for our ScientificCalculator **/
//
//        // For Calculation Operations
//        is CalculatorOperation.SquareRoot -> {
//            val res = sqrt(number2.toDouble())
//            number1 * res
//
//        }
//        is CalculatorOperation.Squared -> {
//            val res = number1 * number1
//            res * number2
//        }
//
//        is CalculatorOperation.Factorial -> {
//            val res = factorial(number1.toInt())
//            res * number2
//        }
//
//        /** IMPORTANT NOTE! I WILL LEAVE THIS IN NULL FOR NOW **/
//        is CalculatorOperation.Inv -> null
//        is CalculatorOperation.Brackets -> null
//        is CalculatorOperation.Sin -> null
//        is CalculatorOperation.Cos -> null
//        is CalculatorOperation.Tan -> null
//        is CalculatorOperation.Log -> null
//        is CalculatorOperation.In -> null
//        null -> return
//    }



//// NOTE! Since All Single Calculator Operations are 'Scientific', this will not involve Standard Scientific Calculator Operations
//// Calculating the result of our Scientific Operations for Calculations that have a single operand
//if (number1 != null && state.operation != null &&  number2 == null) {
//    val result = when(state.operation) {
//        is CalculatorOperation.Add -> null
//        is CalculatorOperation.Subtract-> null
//        is CalculatorOperation.Multiply -> null
//        is CalculatorOperation.Divide -> null
//        is CalculatorOperation.Modulo -> null
//
//        // TODO - WHEN I COME BACK, I THINK I AM GOING TO HAVE TO REVAMP THE ENTIRE LOGIC OF THIS PROJECT USING TUTORIALS AND PROJECTS
//
//        // Our Scientific Calculator Operations.
//        is CalculatorOperation.Squared -> { number1 * number1 }
//        is CalculatorOperation.SquareRoot -> sqrt(number1.toDouble())
//        is CalculatorOperation.Factorial -> { factorial(number1.toInt()) }
//        is CalculatorOperation.Sin -> { sin(Math.toRadians(number1)) }
//        is CalculatorOperation.Cos-> { cos(Math.toRadians(number1)) }
//        is CalculatorOperation.Tan -> { tan(Math.toRadians(number1)) }
//        is CalculatorOperation.Log -> { log10(Math.toRadians(number1)) }
//        is CalculatorOperation.In -> { ln(Math.toRadians(number1)) }
//        is CalculatorOperation.Inv -> null
//        is CalculatorOperation.Brackets -> null
//        null -> return
//    }

//
