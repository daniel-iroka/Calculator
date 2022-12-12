package com.android.calculator.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.android.calculator.CalculatorAction
import com.android.calculator.CalculatorOperation
import com.android.calculator.model.CalculatorHistoryState
import com.android.calculator.model.CalculatorState
import com.android.calculator.ui.theme.ferrari
import kotlin.math.*

/** This is our ViewModel and in Jetpack compose, it is responsible for handling the User actions and click events as well as state in compose.
 *  It will also be responsible for handling UI rotation. **/

private const val TAG = "CalculatorViewModel"

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        // This makes our state accessible by outside classes but still readable
        private set

    // TODO - COME BACK TO SEE WHY THIS DIDN'T WORK. I MAY TEST DOING THIS WITH A SINGLE STATE TO SEE IF IT WORKS

    var historyState by mutableStateOf(CalculatorHistoryState())
        private set

    private var leftBracket by mutableStateOf(true)
    private var check = 0

    // So this function is where and how we will register our click events based on how we set in the Calculator Composable.
    fun onAction(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> {
                state = CalculatorState()
                check = 0
            }
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Brackets -> enterBrackets()
            else -> {}
        }
    }

    // This is the click event for the history part of our Calculator
    fun onActionForHistory(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.ClearHistory -> {
                /** NOTICE! Clearing the calculation history in this manner will be temporary. I will try and better it later. **/
                historyState = historyState.copy(
                    primaryState = ""
                )
                historyState = historyState.copy(
                    secondaryState = ""
                )
                historyState = historyState.copy(
                    time = ""
                )
            }
            //    is CalculatorAction.ClearHistory -> historyState = CalculatorHistoryState()
            else -> {}
        }
    }

    /**
     * We are Basically making the click events possible by modifying the 'state'
     */

    private fun performCalculation() {
        val primaryState = state.primaryTextState
        val primaryStateChar = state.primaryTextState.last()
        val secondaryState = state.secondaryTextState


        if (!(primaryStateChar == '(' || primaryStateChar == '√' || primaryStateChar == '!' || primaryStateChar == '%')) {

            // TODO - NOTE("I will come back to this later on.")

            state = state.copy(
                primaryTextState = secondaryState
            )
            state = state.copy(secondaryTextState = "")


            // TODO - NOW("Now I will try to Implement passing our calculation values to our History screen.")
            /** WELL. THIS DID NOT WORK FOR SOME REASON. **/
            historyState = historyState.copy(
                primaryState = primaryState
            )

            historyState = historyState.copy(
                secondaryState = secondaryState
            )


        } else {
            state = state.copy(
                secondaryTextState = "Format error"
            )

            state = state.copy(
                color = ferrari
            )
        }
    }

    private fun performDeletion() {
        val res: String

        if (state.primaryTextState.isNotBlank()) {
            val value = state.primaryTextState
            val findFirstOpr = value.first()
            val findLastOpr = value.last()

            res = state.primaryTextState.dropLast(1)
            state = state.copy(
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
                state = state.copy(
                    secondaryTextState = ""
                )

                state = state.copy(
                    color = Color.White
                )
            }

//            state = state.copy(
//                color = Color.White
//            )
        } else if (state.operation != null) {
            state = state.copy(
                operation = null
            )
            leftBracket = true
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
            else -> {}
        }

        state = state.copy(
            primaryTextState = state.primaryTextState + (state.operation?.symbol ?: "")
        )
    }

    private fun subtract(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun multiply(operation: CalculatorOperation) {
        if (state.primaryTextState.isNotEmpty()) {
            state = state.copy(operation = operation)
        }
        check += 1
    }

    private fun squareRoot(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
    }

    private fun squared(operation: CalculatorOperation) {
        if (state.primaryTextState.isEmpty()) {
            return
        } else {
            val value = state.primaryTextState.toInt()
            val result = value * value
            state = state.copy(
                secondaryTextState = result.toString()
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
        if (state.primaryTextState.isNotEmpty()) {
            state = state.copy(operation = operation)
        }
        check += 1
    }

    private fun divide(operation: CalculatorOperation) {
        if (state.primaryTextState.isNotEmpty()) {
            state = state.copy(operation = operation)
        }
        check += 1
    }

    private fun modulo(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun sinOpr(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun cosOpr(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun tanOpr(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun logOpr(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun ln(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
    }

    private fun invOpr(operation: CalculatorOperation) {
        state = state.copy(operation = operation)
        check += 1
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
        if (leftBracket) {
            state = state.copy(
                primaryTextState = state.primaryTextState + "("
            )
            leftBracket = false
        } else {
            state = state.copy(
                primaryTextState = state.primaryTextState + ")"
            )
            leftBracket = true
        }
    }

    private fun enterNumber(number: Int) {

        state = state.copy(
            primaryTextState = state.primaryTextState + number
        )
        result(state.primaryTextState)

        when(state.operation) {
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

        state = state.copy(
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

    private fun result(text : String) {
        try {
            val result = eval(text)
            val mainResult = result.toString()
            state = if (check == 0) {
                state.copy(
                    secondaryTextState = ""
                )
            } else {
                state.copy(
                    secondaryTextState = mainResult
                )
            }
//            Log.i(TAG, "LOG- Result $otherResult")
        }
        catch (e : Exception) {
            Log.e(TAG, "ERROR!")
        }
    }

    /** THIS SHIT DID NOT WORK BRO. I WILL CHECK IT LATER. **/
//    private fun result2(text : String) {
//        val engine : ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
//
//        try {
//            val result : Any = engine.eval(text)
//            val mainResult = result.toString()
//
//            state = if (check == 0) {
//                state.copy(
//                    secondaryTextState = ""
//                )
//            } else {
//                state.copy(
//                    secondaryTextState = mainResult
//                )
//            }
//        }
//        catch (e : Exception) {
//            Log.e(TAG, "ERROR!")
//        }
//    }

}
