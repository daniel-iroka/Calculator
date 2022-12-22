package com.android.calculator.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.android.calculator.CalculatorAction
import com.android.calculator.CalculatorOperation
import com.android.calculator.model.CalculatorHistoryState
import com.android.calculator.model.ScientificCalculatorState
import com.android.calculator.ui.theme.ferrari
import kotlin.math.*

/**
 *  This is the ViewModel for our Scientific Calculator.
 */

private const val TAG = "ScientificCalculatorViewModel"

class ScientificCalculatorViewModel : ViewModel() {

    var sciState by mutableStateOf(ScientificCalculatorState())
        private set

    var historyState by mutableStateOf(CalculatorHistoryState())
        private set

    private var leftBracket by mutableStateOf(true)
    private var check = 0

    // Function to Register our Click events
    fun onAction(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> {
                sciState = ScientificCalculatorState()
                check = 0
            }
            is CalculatorAction.Operation -> enterScientificOperations(action.operation)
            is CalculatorAction.Calculate -> performScientificCalculations()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Brackets -> enterBrackets()
            else -> {}
        }
    }

//    private fun clearHistory() {
//        historyState = historyState.copy(
//            historyPrimaryState = ""
//        )
//
//        historyState = historyState.copy(
//            historySecondaryState = ""
//        )
//
//        historyState = historyState.copy(
//            time = ""
//        )
//    }

    private fun performScientificCalculations() {
        val primaryStateChar = sciState.primaryTextState.last()
        val primaryState = sciState.primaryTextState
        val secondaryState = sciState.secondaryTextState

        // TODO - NOTE("I will come back to this later on.")
        if (!(primaryStateChar == '(' || primaryStateChar == '√' || primaryStateChar == '!' || primaryStateChar == '%')) {

            sciState = sciState.copy(
                primaryTextState = secondaryState
            )
            sciState = sciState.copy(secondaryTextState = "")

            // Storing our Calculated Values in the History Screen.
            historyState = historyState.copy(
                historySecondaryState = secondaryState
            )

            historyState = historyState.copy(
                historyPrimaryState = primaryState
            )

        } else {
            sciState = sciState.copy(
                secondaryTextState = "Format error"
            )

            sciState = sciState.copy(
                color = ferrari
            )
        }
    }

    private fun performDeletion() {
        val res: String

        if (sciState.primaryTextState.isNotBlank()) {
            val value = sciState.primaryTextState
//            val findFirstOpr = value.first()
            val findLastOpr = value.last()

            res = sciState.primaryTextState.dropLast(1)
            sciState = sciState.copy(
                primaryTextState = res
            )
            result(res)

            // Will check if the uer input contains any operands and then decrement the check variable
            when (findLastOpr) {
                '+', '-', '×', '÷', '%' -> {
                    check -= 1
                }
            }
            // will do the same as the above but for the scientific operations
//            when {
//                findLastOpr == '(' || findFirstOpr == '√' -> {
//                    check -= 1
//                }
//            }
            // will make sure it only deletes the secondary state when all the operations are gone
            if (!(value.contains('+') || value.contains('-') || value.contains('×') || value.contains('÷') || value.contains('%'))) {
                sciState = sciState.copy(
                    secondaryTextState = ""
                )

                sciState = sciState.copy(
                    color = Color.White
                )
            }

//            state = state.copy(
//                color = Color.White
//            )
        } else if (sciState.operation != null) {
            sciState = sciState.copy(
                operation = null
            )
            leftBracket = true
        }
    }

    private fun enterScientificOperations(operation: CalculatorOperation) {
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
            else -> {}
        }

        sciState = sciState.copy(
            primaryTextState = sciState.primaryTextState + (sciState.operation?.symbol ?: "")
        )
    }

    private fun subtract(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun multiply(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotEmpty()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun squareRoot(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun squared(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isEmpty()) {
            return
        } else {
            val value = sciState.primaryTextState.toInt()
            val result = value * value
            sciState = sciState.copy(
                secondaryTextState = result.toString()
            )
            sciState = sciState.copy(operation = operation)
        }
    }

    private fun factorial(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isEmpty()) {
            return
        } else {
            val value = factorial(sciState.primaryTextState.toInt())
            sciState = sciState.copy(
                secondaryTextState = value.toString()
            )
            sciState = sciState.copy(operation = operation)
        }
    }

    private fun add(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotEmpty()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun divide(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotEmpty()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun modulo(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun sinOpr(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun cosOpr(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun tanOpr(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun logOpr(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun ln(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun invOpr(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun enterDecimal() {
        if (sciState.operation == null && !sciState.primaryTextState.contains(".")
            && sciState.primaryTextState.isNotBlank()
        ) {
            sciState = sciState.copy(
                primaryTextState = sciState.primaryTextState + "."
            )
            return
        }
    }

    private fun enterBrackets() {
        if (leftBracket) {
            sciState = sciState.copy(
                primaryTextState = sciState.primaryTextState + "("
            )
            leftBracket = false
        } else {
            sciState = sciState.copy(
                primaryTextState = sciState.primaryTextState + ")"
            )
            leftBracket = true
        }
    }

    private fun enterNumber(number: Int) {

        sciState = sciState.copy(
            primaryTextState = sciState.primaryTextState + number
        )
        result(sciState.primaryTextState)

        when(sciState.operation) {
            is CalculatorOperation.SquareRoot -> {
                doSquareRoot(number)
            }
            is CalculatorOperation.Modulo -> {
                doModulo()
            }
            else -> {}
        }
    }

    // Our factorial function
    private fun factorial(num : Int) : Long {
        return if (num >= 1)
            num * factorial(num - 1)
        else
            1
    }

    private fun doSquareRoot(number : Int) {
        val result = sqrt(number.toDouble())

        sciState = sciState.copy(
            secondaryTextState = result.toString()
        )


//        val value = state.primaryTextState.first().code
//        when(value) {
//            0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> { "Later I will try and accomplish this..." }
//        }
    }

    private fun doModulo() {
        // Do later...
    }


    // this is the function to perform our Calculation
    private fun eval(value : String): Double {
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
                    else if(eat('÷'.code))x/= parseFactor()
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

    @SuppressLint("LongLogTag")
    private fun result(text : String) {
        try {
            val result = eval(text)
            val mainResult = result.toString()
            sciState = if (check == 0) {
                sciState.copy(
                    secondaryTextState = ""
                )
            } else {
                sciState.copy(
                    secondaryTextState = mainResult
                )
            }
        }
        catch (e : Exception) {
            Log.e(TAG, "ERROR!")
        }
    }
}