package com.android.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/** This is our ViewModel and in Jetpack compose, it is responsible for handling the User events and actions as well as state in compose. It will also be responsible for handling UI rotation. **/

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        // This means that we can change the state from the outside but we can still read it
        private set

    // TODO - When I come back, I will continue in the making of this Project which is specifically handling the USER events.

    fun onAction(action : CalculatorAction) {

    }
}