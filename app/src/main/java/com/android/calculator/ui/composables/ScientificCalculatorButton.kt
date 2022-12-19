package com.android.calculator.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.android.calculator.ui.theme.Shapes

@Composable
fun ScientificCalculatorButton(
    symbol : String,
    color : Color = Color.White,
    fontSize : TextUnit = 20.sp,
    modifier : Modifier,
    onCLick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(Shapes.small)
            .clickable { onCLick() }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = fontSize,
            color = color,
            style = TextStyle(
                fontWeight = Bold
            )
        )
    }
}