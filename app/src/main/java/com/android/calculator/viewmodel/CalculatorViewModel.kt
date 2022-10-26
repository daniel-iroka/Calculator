package com.android.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.calculator.CalculatorAction
import com.android.calculator.CalculatorOperation
import com.android.calculator.model.CalculatorState
import kotlin.math.*

/** This is our ViewModel and in Jetpack compose, it is responsible for handling the User actions and click events as well as state in compose.
 *  It will also be responsible for handling UI rotation. **/

private const val TAG = "CalculatorViewModel"

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
        when(operation) {
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
            else -> {}
        }

        state = state.copy(
            primaryTextState = state.primaryTextState + (state.operation?.symbol ?: "")
        )
    }

    private fun subtract(operation: CalculatorOperation) {
        val tvState = state.primaryTextState
        if (!tvState.get(index = tvState.length - 1).equals("-")) {
            state = state.copy(operation = operation)
        }
    }

    private fun multiply(operation: CalculatorOperation) {
        val tvState = state.primaryTextState
        if (!tvState.get(index = tvState.length - 1).equals("×")) {
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
                secondaryTextState = calculatedResult
            )
            state = state.copy(operation = operation)
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

            state = state.copy(operation = operation)
        }
    }

    private fun factorial(operation: CalculatorOperation) {
        if (state.primaryTextState.isEmpty()) {
            return
        } else {
            val value = factorial(state.primaryTextState.toInt())
            state = state.copy(
                secondaryTextState = value.toString()
            )

            state = state.copy(operation = operation)
        }
    }

    private fun add(operation: CalculatorOperation) {
        state = state.copy(operation = operation)

        // TODO - WHEN I COME BACK TOMORROW, I WILL SEE IF I CAN IMPROVE THIS THING WHICH IS SEE IF IT CAN IMMEDIATELY GET CALCULATED AS WE ENTER THE VALUES.

        val result = evaluate(state.primaryTextState)
        state = state.copy(secondaryTextState = result.toString())
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
                    if (eat('+'.code))x += parseTerm()
                    else if (eat('-'.code))x -= parseTerm()
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if(eat('*'.toInt()))x *= parseFactor()
                    else if(eat('/'.toInt()))x/= parseFactor()
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt()))return parseFactor()
                if (eat('-'.toInt()))return parseFactor()

                var x: Double
                val startPos = pos

                if (eat('('.toInt())) {
                    x = parseExpression()
                    eat(')'.toInt())
                } else if(ch>= '0'.toInt() && ch<= '9'.toInt() || ch=='.'.toInt()) {

                    while (ch>='0'.toInt() && ch<= '9'.toInt() || ch=='.'.toInt())nextChar()
                    x = value.substring(startPos, pos).toDouble()

                } else if (ch>= 'a'.toInt() && ch<='z'.toInt()) {
                    while (ch>= 'a'.toInt() && ch<='z'.toInt())nextChar()
                    val func = value.substring(startPos, pos)
                    x = parseFactor()
                    if (func == "sqrt") {
                        x = sqrt(x)
                    }
                    else if (func == "sin") {
                        x = sin(Math.toRadians(x))
                    }
                    else if (func == "cos") {
                        x = cos(Math.toRadians(x))
                    }
                    else if (func == "tan") {
                        x = tan(Math.toRadians(x))
                    }
                    else if (func == "log") {
                        x = log10(x)
                    }
                    else if (func == "ln") {
                        x = ln(x)
                    }
                } else {
                    throw RuntimeException("Unexpected : " + ch.toChar())
                }
                if (eat('^'.toInt()))x = x.pow(parseFactor())
                return x
            }
        }.parse()
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}