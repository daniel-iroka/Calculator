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
        }
    }

    // We are Basically making the click events possible by modifying the 'state'

    // TODO - NOW IS TO SEE IF I CAN REDUCE THE SIZE OF THIS TEXT WHEN I SEE THAT THE CHARACTER NUMBER EXCEEDS A CERTAIN SIZE(THAT IS FOR BIG CALCULATIONS)

    private fun performCalculation() {
        val primaryState = state.primaryTextState
        val secondaryState = state.secondaryTextState

        if (secondaryState.isNotEmpty()) {
            state = state.copy(
                primaryTextState = secondaryState
            )
            state = state.copy(secondaryTextState = "")
        } else {
            val value = evaluate(primaryState)
            val fValue = value.toString()

            state = state.copy(
                primaryTextState = fValue
            )
            state = state.copy(secondaryTextState = "")
        }
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
            is CalculatorOperation.Sin -> sinOpr(operation)
            is CalculatorOperation.Cos -> cosOpr(operation)
            is CalculatorOperation.Tan -> tanOpr(operation)
            is CalculatorOperation.Log -> logOpr(operation)
            is CalculatorOperation.In -> ln(operation)
            is CalculatorOperation.Inv -> invOpr(operation)
            is CalculatorOperation.Bracket1 -> enterBracket1(operation)
            is CalculatorOperation.Bracket2 -> enterBracket2(operation)
            else -> {}
        }

        state = state.copy(
            primaryTextState = state.primaryTextState + (state.operation?.symbol ?: "")
        )
    }

    // TODO - WHEN I COME BACK, I WILL TEST THIS NEWLY REFACTORED CODE I JUST ADDED WHICH IS THE SCIENTIFIC CALCULATION FUNCTIONS TO BE SPECIFIC.

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
    }

    private fun divide(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun modulo(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun sinOpr(operation: CalculatorOperation) {

        // TODO - WHEN I COME BACK, I WILL TEST THIS THING I JUST ADDED NOW AND IF IT WORKS I WILL ALSO REFACTOR THE OTHER OPERATIONS SUCH AS tan, cos, ln and log

        val value = sin(Math.toRadians(state.primaryTextState.toDouble()))
        state = state.copy(
            secondaryTextState = value.toString()
        )

        state = state.copy(operation = operation)
    }

    private fun cosOpr(operation: CalculatorOperation) {
        val value = cos(Math.toRadians(state.primaryTextState.toDouble()))
        state = state.copy(
            secondaryTextState = value.toString()
        )
        state = state.copy(operation = operation)
    }

    private fun tanOpr(operation: CalculatorOperation) {
        val value = tan(Math.toRadians(state.primaryTextState.toDouble()))
        state = state.copy(
            secondaryTextState = value.toString()
        )
        state = state.copy(operation = operation)
    }

    private fun logOpr(operation: CalculatorOperation) {
        val value = log10(state.primaryTextState.toDouble())
        state = state.copy(
            secondaryTextState = value.toString()
        )
        state = state.copy(operation = operation)
    }

    private fun ln(operation: CalculatorOperation) {
        val value = tan(Math.toRadians(state.primaryTextState.toDouble()))
        state = state.copy(
            secondaryTextState = value.toString()
        )
        state = state.copy(operation = operation)
    }

    private fun invOpr(operation: CalculatorOperation) {
        val value = ln(state.primaryTextState.toDouble())
        state = state.copy(
            secondaryTextState = value.toString()
        )

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

    private fun enterBracket1(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun enterBracket2(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun enterNumber(number: Int) {

        // TODO - WHEN I COME BACK, I WILL SEE IF THERE IS ANYHOW I CAN AUTOMATICALLY ADJUST TEXT SIZE AS IT FIILLS UP THE SCREEN.
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
                ch = if (++pos <value.length) value[pos].code else -1
            }

            fun eat(charToEdt: Int): Boolean {
                while (ch== ' '.code)nextChar()

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
                    if(eat('×'.code))x *= parseFactor()
                    else if(eat('/'.code))x/= parseFactor()
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code))return parseFactor()
                if (eat('-'.code))return parseFactor()

                var x: Double
                val startPos = pos

                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if(ch>= '0'.code && ch<= '9'.code || ch== '.'.code) {

                    while (ch>= '0'.code && ch<= '9'.code || ch== '.'.code)nextChar()
                    x = value.substring(startPos, pos).toDouble()

                } else if (ch>= 'a'.code && ch<= 'z'.code) {
                    while (ch>= 'a'.code && ch<= 'z'.code)nextChar()
                    val func = value.substring(startPos, pos)
                    x = parseFactor()
                    when (func) {
                        "sqrt" -> {
                            x = sqrt(x)
                        }
                        "sin" -> {
                            x = sin(Math.toRadians(x))
                        }
                        "cos" -> {
                            x = cos(Math.toRadians(x))
                        }
                        "tan" -> {
                            x = tan(Math.toRadians(x))
                        }
                        "log" -> {
                            x = log10(x)
                        }
                        "ln" -> {
                            x = ln(x)
                        }
                    }
                } else {
                    throw RuntimeException("Unexpected char : " + ch.toChar())
                }
                if (eat('^'.code))x = x.pow(parseFactor())
                return x
            }
        }.parse()
    }

    // I don't know if i will need this but i will just comment on it for now
//    companion object {
//        private const val MAX_NUM_LENGTH = 8
//    }
}