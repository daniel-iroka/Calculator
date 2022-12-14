package com.android.calculator.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *  This is our Custom OR Reusable Modifiers file which is to be accessed  across our Project in form of kotlin Extension functions.
 */

// For Standard Calculator buttons
fun Modifier.standardCalculatorModifiers(color : Color) =
    this.width(80.dp)
        .height(65.dp)
        .background(color)
        .aspectRatio(1f) // basically determines the size of an item on the screen


// For Scientific Calculator buttons
fun Modifier.scientificCalculatorModifiers(color :Color) =
    this.width(70.dp)
        .height(60.dp)
        .background(color)
        .aspectRatio(1f)


// For Scientific Operation buttons.
fun Modifier.scientificOperationModifiers() =
    this.width(50.dp)
        .height(30.dp)