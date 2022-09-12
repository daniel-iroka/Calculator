package com.android.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.android.calculator.ui.theme.Shapes

@Composable
fun CalculatorButton(
    symbol : String,
    color : Color = Color.White,
    modifier : Modifier,
    onCLick: () -> Unit
) {
    // This is a rounded Box here because of the clip CircleShape
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(Shapes.small)
            .clickable { onCLick() } // This will only make our 'Box' which is a text clickable
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            color = color,
        )
    }
}