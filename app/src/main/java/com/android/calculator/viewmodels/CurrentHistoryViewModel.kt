package com.android.calculator.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.calculator.CalculatorAction
import com.android.calculator.model.CalculatorHistoryState

private const val TAG = "CurrentHistoryViewModel"

class CurrentHistoryViewModel : ViewModel() {

    var currentHistoryState by mutableStateOf(CalculatorHistoryState())
        private set

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.ClearHistory -> clearHistory()
            else -> {}
        }
    }

    private fun clearHistory() {
        currentHistoryState = currentHistoryState.copy(
            historyPrimaryState = ""
        )

        currentHistoryState = currentHistoryState.copy(
            historySecondaryState = ""
        )
        Log.i(TAG, "This is our currentHistory State in our ViewModel : $currentHistoryState")
    }
}

