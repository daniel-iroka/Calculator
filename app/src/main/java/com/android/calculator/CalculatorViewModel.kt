package com.android.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/** This is our ViewModel and in Jetpack compose, it is responsible for handling User events as well as state in compose. It will also be responsible for handling UI rotation. **/

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        // This means that we can change the state from the outside but we can still read it
        private set
}