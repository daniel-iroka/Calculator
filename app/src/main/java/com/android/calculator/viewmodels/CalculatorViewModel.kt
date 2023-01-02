package com.android.calculator.viewmodels

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.android.calculator.CalculatorAction
import com.android.calculator.CalculatorOperation
import com.android.calculator.model.CalculatorHistoryState
import com.android.calculator.model.CalculatorState
import com.android.calculator.model.ScientificCalculatorState
import com.android.calculator.ui.theme.ferrari
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.math.*

/**
 *    This is the ViewModel for our 'StandardCalculator' which in in Jetpack compose, is responsible for handling the User actions and click events(eventHandler)
 *    as well as the 'state' in compose.
 */

private const val TAG = "CalculatorViewModel"

// Todo - FIX("When I come back, if I am able to solve this list issue, I Will try as much to reduce the repetition of code here and try to reuse whatever function or Implementation I have set up.")

class CalculatorViewModel : ViewModel() {

    var strState by mutableStateOf(CalculatorState())
        // This makes our state accessible by outside classes but still readable
        private set

    var sciState by mutableStateOf(ScientificCalculatorState())
        private set

    var historyState = mutableListOf<CalculatorHistoryState>()

    private var leftBracket by mutableStateOf(true)
    private var check = 0
    private var check1 = 0

    // Todo - When I come back tomorrow, the other bug I will check will now be to why there is an extra space after I add an item to a list

    // Function to Register our Click events
    fun onActionForStandardOpr(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterStrNumber(action.number)
            is CalculatorAction.Decimal -> enterStrDecimal()
            is CalculatorAction.Clear -> {
                strState = CalculatorState()
                check = 0
            }
            is CalculatorAction.ClearHistory -> historyState.clear()
            is CalculatorAction.Operation -> enterStrOperations(action.operation)
            is CalculatorAction.Calculate -> performStrCalculations()
            is CalculatorAction.Delete -> performStrDeletion()
            is CalculatorAction.Brackets -> enterStrBrackets()
        }
    }

    fun onActionForScientificOpr(action : CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterSciNumber(action.number)
            is CalculatorAction.Decimal -> enterSciDecimal()
            is CalculatorAction.Clear -> {
                sciState = ScientificCalculatorState()
                check1 = 0
            }
            is CalculatorAction.Operation -> enterSciOperations(action.operation)
            is CalculatorAction.Calculate -> performSciCalculations()
            is CalculatorAction.Delete -> performSciDeletion()
            is CalculatorAction.Brackets -> enterSciBrackets()
            else -> {}
        }
    }

    fun api() = flow {
        delay(2000)
        emit("Testing")
    }

    // We are Basically making the click events possible by modifying the 'state'
    private fun performStrCalculations() {
        val primaryStateChar = strState.primaryTextState.last()
        val primaryState = strState.primaryTextState
        val secondaryState = strState.secondaryTextState

        if ((primaryStateChar == '(' || primaryStateChar == '%').not()) {

            strState = strState.copy(
                primaryTextState = secondaryState
            )
            strState = strState.copy(secondaryTextState = "")

            historyState.add(CalculatorHistoryState(
                historySecondaryState = secondaryState
            ))

            historyState.add(CalculatorHistoryState(
                historyPrimaryState = primaryState
            ))
        } else {
            strState = strState.copy(
                secondaryTextState = "Format error"
            )

            strState = strState.copy(
                color = ferrari
            )
        }

    }

    private fun performSciCalculations() {
        val primaryStateChar = sciState.primaryTextState.last()
        val primaryState = sciState.primaryTextState
        val secondaryState = sciState.secondaryTextState

        if ((primaryStateChar == '(' || primaryStateChar == '%').not()) {

            sciState = sciState.copy(
                primaryTextState = secondaryState
            )
            sciState = sciState.copy(secondaryTextState = "")

            historyState.add(CalculatorHistoryState(
                historySecondaryState = secondaryState
            ))

            historyState.add(CalculatorHistoryState(
                historyPrimaryState = primaryState
            ))
        } else {
            sciState = sciState.copy(
                secondaryTextState = "Format error"
            )

            sciState = sciState.copy(
                color = ferrari
            )
        }
    }

    private fun performStrDeletion() {
        val res: String

        if (strState.primaryTextState.isNotBlank()) {
            val value = strState.primaryTextState
            val findLastOpr = value.last()

            res = strState.primaryTextState.dropLast(1)
            strState = strState.copy(
                primaryTextState = res
            )
            strResult(res)

            // Checks if the uer input contains any of these operands as the last and then decrements the check variable
            when (findLastOpr) {
                '+', '-', '×', '÷', '%' -> {
                    check -= 1
                }
            }

            // This makes sure it only deletes the secondary state when all the operations are gone
            if ((value.contains('+') || value.contains('-') || value.contains('×') || value.contains('÷') || value.contains('%')).not()) {
                strState = strState.copy(
                    secondaryTextState = ""
                )

                strState = strState.copy(
                    color = Color.White
                )
            }

        } else if (strState.operation != null) {
            strState = strState.copy(
                operation = null
            )
            leftBracket = true
        }
    }

    private fun performSciDeletion() {
        val res: String

        if (sciState.primaryTextState.isNotBlank()) {
            val value = sciState.primaryTextState
            val findLastOpr = value.last()

            res = sciState.primaryTextState.dropLast(1)
            sciState = sciState.copy(
                primaryTextState = res
            )
            strResult(res)

            // Checks if the uer input contains any of these operands as the last and then decrements the check variable
            when (findLastOpr) {
                '+', '-', '×', '÷', '%' -> {
                    check -= 1
                }
            }

            // This makes sure it only deletes the secondary state when all the operations are gone
            if ((value.contains('+') || value.contains('-') || value.contains('×') || value.contains('÷') || value.contains('%')).not()) {
                sciState = sciState.copy(
                    secondaryTextState = ""
                )

                sciState = sciState.copy(
                    color = Color.White
                )
            }

        } else if (sciState.operation != null) {
            sciState = sciState.copy(
                operation = null
            )
            leftBracket = true
        }
    }

    //

    private fun enterStrOperations(operation: CalculatorOperation) {
        when(operation) {
            is CalculatorOperation.Add -> add(operation)
            is CalculatorOperation.Subtract -> subtract(operation)
            is CalculatorOperation.Multiply -> multiply(operation)
            is CalculatorOperation.Divide -> divide(operation)
            is CalculatorOperation.Modulo -> modulo(operation)
            else -> {}
        }

        strState = strState.copy(
            primaryTextState = strState.primaryTextState + (strState.operation?.symbol ?: "")
        )
    }

    private fun enterSciOperations(operation: CalculatorOperation) {
        when(operation) {
            is CalculatorOperation.Add -> sciAdd(operation)
            is CalculatorOperation.Subtract -> sciSubtract(operation)
            is CalculatorOperation.Multiply -> sciMultiply(operation)
            is CalculatorOperation.Divide -> sciDivide(operation)
            is CalculatorOperation.Modulo -> sciModulo(operation)
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

        strState = strState.copy(
            primaryTextState = strState.primaryTextState + (strState.operation?.symbol ?: "")
        )

        sciState = sciState.copy(
            primaryTextState = sciState.primaryTextState + (sciState.operation?.symbol ?: "")
        )
    }

    private fun subtract(operation: CalculatorOperation) {
        strState = strState.copy(operation = operation)
        check += 1
    }

    private fun multiply(operation: CalculatorOperation) {
        if (strState.primaryTextState.isNotBlank()) {
            strState = strState.copy(operation = operation)
        }
        check += 1
    }

    private fun add(operation: CalculatorOperation) {
        if (strState.primaryTextState.isNotBlank()) {
            strState = strState.copy(operation = operation)
        }
        check += 1
    }

    private fun divide(operation: CalculatorOperation) {
        if (strState.primaryTextState.isNotBlank()) {
            strState = strState.copy(operation = operation)
        }
        check += 1
    }

    private fun modulo(operation: CalculatorOperation) {
        strState = strState.copy(operation = operation)
        check += 1
    }


    private fun sciSubtract(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
        check += 1
    }

    private fun sciMultiply(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotBlank()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun sciAdd(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotBlank()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun sciDivide(operation: CalculatorOperation) {
        if (sciState.primaryTextState.isNotBlank()) {
            sciState = sciState.copy(operation = operation)
        }
        check += 1
    }

    private fun sciModulo(operation: CalculatorOperation) {
        sciState = sciState.copy(operation = operation)
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

    private fun enterStrDecimal() {
        // This will only execute when == true
        if (strState.operation == null && !strState.primaryTextState.contains(".")
            && strState.primaryTextState.isNotBlank()
        ) {
            strState = strState.copy(
                primaryTextState = strState.primaryTextState + "."
            )
            return
        }
    }

    private fun enterSciDecimal() {
        // This will only execute when == true
        if (sciState.operation == null && !sciState.primaryTextState.contains(".")
            && sciState.primaryTextState.isNotBlank()
        ) {
            sciState = sciState.copy(
                primaryTextState = sciState.primaryTextState + "."
            )
            return
        }
    }

    private fun enterStrBrackets() {
        if (leftBracket) {
            strState = strState.copy(
                primaryTextState = strState.primaryTextState + "("
            )
            leftBracket = false
        } else {
            strState = strState.copy(
                primaryTextState = strState.primaryTextState + ")"
            )
            leftBracket = true
        }
    }

    private fun enterSciBrackets() {
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

    private fun enterStrNumber(number: Int) {

        strState = strState.copy(
            primaryTextState = strState.primaryTextState + number
        )
        strResult(strState.primaryTextState)

        when(strState.operation) {
            is CalculatorOperation.Modulo -> {
                doModulo()
            }
            else -> {}
        }
    }

    private fun enterSciNumber(number: Int) {

        sciState = sciState.copy(
            primaryTextState = sciState.primaryTextState + number
        )
        sciResult(sciState.primaryTextState)

        when(sciState.operation) {
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

    private fun strResult(text : String) {
        try {
            val result = eval(text)
            val mainResult = result.toString()
            strState = if (check == 0) {
                strState.copy(
                    secondaryTextState = ""
                )
            } else {
                strState.copy(
                    secondaryTextState = mainResult
                )
            }
        }
        catch (e : Exception) {
            Log.e(TAG, "ERROR!")
        }
    }

    private fun sciResult(text : String) {
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
