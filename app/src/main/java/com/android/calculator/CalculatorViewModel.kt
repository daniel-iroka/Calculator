package com.android.calculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
            is CalculatorAction.Clear -> state = CalculatorState() // will just clear everything and return it to the initial state which is an empty string
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
        }
    }

    // We are Basically making the click events possible by modifying the 'state'

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                // will drop the last digit of the value entered
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        // Calculating the result of our standard and Scientific Calculator operations that have two operands
        if (number1 != null && number2 != null){
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Modulo -> number1 % number2

                /** Operations for our ScientificCalculator **/

                // For Calculation Operations
                is CalculatorOperation.SquareRoot -> {
                    val res = sqrt(number2.toDouble())
                    number1 * res

                }
                is CalculatorOperation.Squared -> {
                    val res = number1 * number1
                    res * number2
                }

                is CalculatorOperation.Factorial -> {
                    val res = factorial(number1.toInt())
                    res * number2
                }

                /** IMPORTANT NOTE! I WILL LEAVE THIS IN NULL FOR NOW **/
                is CalculatorOperation.Inv -> null
                is CalculatorOperation.Brackets -> null
                is CalculatorOperation.Sin -> null
                is CalculatorOperation.Cos -> null
                is CalculatorOperation.Tan -> null
                is CalculatorOperation.Log -> null
                is CalculatorOperation.In -> null
                null -> return
            }

            state = state.copy(
                // will assign the result to the new 'result' parameter in our state
                result = result.toString().take(15)
            )
        }


        // NOTE! Since All Single Calculator Operations are 'Scientific', this will not involve Standard Scientific Calculator Operations
        // Calculating the result of our Scientific Operations for Calculations that have a single operand
        if (number1 != null && state.operation != null &&  number2 == null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> null
                is CalculatorOperation.Subtract-> null
                is CalculatorOperation.Multiply -> null
                is CalculatorOperation.Divide -> null
                is CalculatorOperation.Modulo -> null

                // TODO - WHEN I COME BACK, I THINK I AM GOING TO HAVE TO REVAMP THE ENTIRE LOGIC OF THIS PROJECT USING TUTORIALS AND PROJECTS

                // Our Scientific Calculator Operations.
                is CalculatorOperation.Squared -> { number1 * number1 }
                is CalculatorOperation.SquareRoot -> sqrt(number1.toDouble())
                is CalculatorOperation.Factorial -> { factorial(number1.toInt()) }
                is CalculatorOperation.Sin -> { sin(Math.toRadians(number1)) }
                is CalculatorOperation.Cos-> { cos(Math.toRadians(number1)) }
                is CalculatorOperation.Tan -> { tan(Math.toRadians(number1)) }
                is CalculatorOperation.Log -> { log10(Math.toRadians(number1)) }
                is CalculatorOperation.In -> { ln(Math.toRadians(number1)) }
                is CalculatorOperation.Inv -> null
                is CalculatorOperation.Brackets -> null
                null -> return
            }

            state = state.copy(
                result = result.toString().take(15)
            )
            Log.i(TAG, "This function was called.")
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)

        }
    }

    private fun enterBracket() {
        // Complete Later...
    }

    private fun enterDecimal() {
        // we want to ONLY enter a decimal when a state is not blank, does not already contain a decimal and the CalculatorOperation is null
        // so this will only append the decimal when all is true but not when even one single one is false, basically
        if (state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()
        ) {
            // TODO - WHEN I COME BACK, I MAY HAVE TO USE A SINGLE STATE FOR OUR RESULT.
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        // For our second state
        if (!state.number2.contains(".") && state.number2.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }

        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    // Our factorial function
    private fun factorial(num : Int) : Long {
        return if (num >= 1)
            num * factorial(num - 1)
         else
            1
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}