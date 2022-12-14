package com.android.calculator.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** This is file is where we will be writing our Reusable or Custom Modifiers to be used across our Project in form of kotlin Extension functions. **/

// Todo - IMPLEMENTATION("When I come back, I will continue with the Implementation of the reusable Modifiers. Specifically the Scientific Calculator Part.")

// Custom Modifiers for our Standard Calculator.
fun Modifier.standardCalculatorModifiers(color : Color) =
    this.width(80.dp)
        .height(65.dp)
        .background(color)
        .aspectRatio(1f) // basically determines the size of a composable on the screen


// Custom Modifiers for our Scientific Calculator.
fun Modifier.scientificCalculatorModifiers(color :Color) =
    this.width(70.dp)
        .height(60.dp)
        .background(color)
        .aspectRatio(1f)




















